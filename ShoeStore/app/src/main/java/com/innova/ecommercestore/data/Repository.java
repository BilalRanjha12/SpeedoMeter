package com.innova.ecommercestore.data;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.FractionRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.FieldValue;
import com.innova.ecommercestore.models.ImageUploadResponse;
import com.innova.ecommercestore.models.Product;
import com.innova.ecommercestore.models.Response;
import com.innova.ecommercestore.models.User;
import com.innova.ecommercestore.ui.mainactivity.fragments.availablecardsfragment.AvailableCardsFragment;
import com.innova.ecommercestore.utils.Event;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Repository {
    private static final String IMAGE_UPLOAD = "ImageUpload";
    private Application application;
    private FirebaseFirestore firebaseFirestore;
    private Product product;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private CollectionReference collectionReference;
    private CollectionReference trendingcollectionReference;
    private CollectionReference electroniccollectionReference;
    private CollectionReference kidscollectionReference;
    public MutableLiveData<List<Product>> productsLiveData = new MutableLiveData<>();
    public MutableLiveData<Integer> uploadComplete = new MutableLiveData<>();
    private static Integer uploadStatus;
    private List<Product> productList = new ArrayList<>();
    private boolean productsQueryComplete;
    private boolean favAndCartQueryComplete;
    private List<String> favNameList = new ArrayList<>();
    private List<String> cartProductsNames = new ArrayList<>();
    private ArrayList<Product> favProducts = new ArrayList<>();

    public static String getImageUpload() {
        return IMAGE_UPLOAD;
    }

    private ArrayList<Product> trendingProducts = new ArrayList<>();
    private ArrayList<Product> elctronicProducts = new ArrayList<>();
    private ArrayList<Product> kidProducts = new ArrayList<>();
    private List<Product> productsInCart = new ArrayList<>();

    private MutableLiveData<Event<User>> userLiveData = new MutableLiveData<>();

    private MutableLiveData<ImageUploadResponse> imageUploadComplete = new MutableLiveData<>();


    private MutableLiveData<String> userProfileImage = new MutableLiveData<>();
    private FirebaseAuth firebaseAuth;
    private static Repository repository;

    private static final int RESPONSE_SUCCESSFUL = 1;

    private static final int RESPONSE_FAILED = 0;
    private static final int RESPONSE_PRODUCT_ADDED_TO_FAV = 1;

    private MutableLiveData<Event<Response>> response = new MutableLiveData<>();

    private Repository(Application application) {
        this.application = application;
        initiateDatabase();
        initiateFireBaseAuth();
    }

    public static Repository getRepository(Application application) {
        if (repository == null) {
            repository = new Repository(application);
        }
        return repository;
    }

    public void initiateDatabase() {
        if (firebaseFirestore == null) {
            productsQueryComplete = false;
            favAndCartQueryComplete = false;
            firebaseFirestore = FirebaseFirestore.getInstance();
            collectionReference = firebaseFirestore.collection("products");
            trendingcollectionReference = firebaseFirestore.collection("trending");
            kidscollectionReference = firebaseFirestore.collection("kids");
            electroniccollectionReference = firebaseFirestore.collection("electronics");
        }
    }

    public void initiateFireBaseAuth() {
        if (firebaseAuth == null) {
            firebaseAuth = FirebaseAuth.getInstance();
        }
    }

    public void createProduct(String productName, String productCategory, int price, String productDescription, List<String> images, List<String> tags) {
        product = new Product(productName, productCategory, price, productDescription, images, tags);

        uploadProduct(product);

    }

    public void uploadImage(Uri imageUri, String productCategory, String productName, int imageNo) {
        if (storage == null) {
            storage = FirebaseStorage.getInstance();

        }
        storageReference = storage.getReference("products_images").child(productCategory).child(productName + imageNo);


        if (imageUri != null) {

            storageReference.putFile(imageUri)
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e(IMAGE_UPLOAD, "failed to upload");
                            ImageUploadResponse imageUploadResponse = new ImageUploadResponse();
                            imageUploadResponse.setImageUploaded(false);
                            imageUploadComplete.postValue(imageUploadResponse);
                        }
                    })
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Log.i(IMAGE_UPLOAD, "success!");
                        }
                    })
                    .continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                        @Override
                        public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                            if (!task.isSuccessful()) {
                                Log.e(IMAGE_UPLOAD, "could not get dload url");
                                throw task.getException();

                            }
                            return storageReference.getDownloadUrl();
                        }
                    })
                    .addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            String downloadUrl = task.getResult().toString();

                            ImageUploadResponse imageUploadResponse = new ImageUploadResponse();
                            imageUploadResponse.setImageUploaded(true);
                            imageUploadResponse.setDownloadUrl(downloadUrl);

                            imageUploadComplete.postValue(imageUploadResponse);
                        }
                    });
        }
    }

    public void uploadProfileImage(Uri pofileUri) {
        if (storage == null) {
            storage = FirebaseStorage.getInstance();

        }
        final String[] profileUrl = new String[1];
        storageReference = storage.getReference("user_images").child(FirebaseAuth.getInstance().getUid());


        if (pofileUri != null) {

            storageReference.putFile(pofileUri)
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e(IMAGE_UPLOAD, "failed to upload");
                            ImageUploadResponse imageUploadResponse = new ImageUploadResponse();
                            imageUploadResponse.setImageUploaded(false);
                            imageUploadComplete.postValue(imageUploadResponse);
                        }
                    })
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Log.i(IMAGE_UPLOAD, "success!");
                        }
                    })
                    .continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                        @Override
                        public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                            if (!task.isSuccessful()) {
                                Log.e(IMAGE_UPLOAD, "could not get dload url");
                                throw task.getException();

                            }
                            return storageReference.getDownloadUrl();
                        }
                    })
                    .addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            String downloadUrl = task.getResult().toString();
                            profileUrl[0] = downloadUrl;

                            final DocumentReference documentReference = firebaseFirestore.collection("users").document(firebaseAuth.getCurrentUser().getUid());
                            Map<String, Object> updates = new HashMap<>();
                            updates.put("image", downloadUrl);
                            documentReference.update(updates).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                }
                            });


                            ImageUploadResponse imageUploadResponse = new ImageUploadResponse();
                            imageUploadResponse.setImageUploaded(true);
                            imageUploadResponse.setDownloadUrl(downloadUrl);

                            imageUploadComplete.postValue(imageUploadResponse);
                        }
                    });

//            final DocumentReference documentReference = firebaseFirestore.collection("users").document(firebaseAuth.getCurrentUser().getUid());
//
//            Map<String, Object> updates = new HashMap<>();
//            updates.put("image", profileUrl[0]);
//
//            documentReference.update(updates).addOnCompleteListener(new OnCompleteListener<Void>() {
//                @Override
//                public void onComplete(@NonNull Task<Void> task) {
//                }
//            });
        }
    }
    private void uploadProduct(Product product) {
//                collectionReference
        electroniccollectionReference
                .document(product.getName())
                .set(product)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            uploadStatus = -1;
                            uploadComplete.setValue(uploadStatus);

                        } else {
                            uploadStatus = 1;
                            uploadComplete.setValue(uploadStatus);
                        }
                    }
                });

    }

    public void getProducts() {

        collectionReference.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            productList.clear();
                            for (DocumentSnapshot documentSnapshot : task.getResult()) {
                                Product product = documentSnapshot.toObject(Product.class);
                                productList.add(product);
                            }
                            productsQueryComplete = true;
                            setFavProductList();
                            setCartProductList();

                        }
                    }
                });
    }

    public void getFavAndCartProducts() {
        firebaseAuth = FirebaseAuth.getInstance();

        final DocumentReference documentReference = firebaseFirestore.collection("users").document(firebaseAuth.getCurrentUser().getUid());


        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    favNameList.clear();
                    favNameList = (List<String>) task.getResult().get("favoriteProducts");

                    cartProductsNames.clear();
                    cartProductsNames = (List<String>) task.getResult().get("productsInCart");


                    favAndCartQueryComplete = true;

                    setFavProductList();
                    setCartProductList();


                } else {
                    Log.e("Fav products Query", task.getException().getMessage());
                }
            }
        });
    }

    public void getTrending() {
        trendingcollectionReference.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            trendingProducts.clear();
                            for (DocumentSnapshot documentSnapshot : task.getResult()) {
                                Product product = documentSnapshot.toObject(Product.class);
                                trendingProducts.add(product);
                            }
                        }
                    }
                });
    }

    public void getKidsProducts()
    {
        kidscollectionReference.get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        kidProducts.clear();
                        for (DocumentSnapshot documentSnapshot : task.getResult()) {
                            Product product = documentSnapshot.toObject(Product.class);
                            kidProducts.add(product);
                        }
                    }
                }
            });
    }
    public void getElectronics() {
        electroniccollectionReference.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            elctronicProducts.clear();
                            for (DocumentSnapshot documentSnapshot : task.getResult()) {
                                Product product = documentSnapshot.toObject(Product.class);
                                elctronicProducts.add(product);
                            }
                        }
                    }
                });
    }


    private void setFavProductList() {
        if (productsQueryComplete && favAndCartQueryComplete) {
            String productName;
            favProducts.clear();
            productsInCart.clear();
            for (int i = 0; i < productList.size(); i++) {
                productName = productList.get(i).getName();
                for (int j = 0; j < favNameList.size(); j++) {
                    if (productName != null) {
                        if (productName.equals(favNameList.get(j))) {
                            productList.get(i).setFavorite(true);
                            favProducts.add(productList.get(i));
                            break;
                        }
                    }

                }


            }
        }
    }

    public void emptyCart(Context fragment) {
        boolean isEmpty = false;
        productsInCart.clear();
        cartProductsNames.clear();

        // now make firestore field value for products in cart empty
        firebaseAuth = FirebaseAuth.getInstance();
        final DocumentReference documentReference = firebaseFirestore.collection("users").document(firebaseAuth.getCurrentUser().getUid());
        List<String> productsInCart = new ArrayList<>();
        Map<String, Object> updates = new HashMap<>();
        updates.put("productsInCart", productsInCart);

        documentReference.update(updates).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(fragment, "Your order has been Placed!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setCartProductList() {
        if (productsQueryComplete && favAndCartQueryComplete) {
            String productName;

            productsInCart.clear();

            for (int i = 0; i < productList.size(); i++) {
                productName = productList.get(i).getName();
                for (int j = 0; j < cartProductsNames.size(); j++) {
                    if (productName != null) {
                        if (productName.equals(cartProductsNames.get(j))) {
                            productList.get(i).setInCart(true);
                            productsInCart.add(productList.get(i));
                            break;
                        }
                    }

                }


            }
            favAndCartQueryComplete = false;
            productsQueryComplete = false;
            productsLiveData.setValue(productList);
        }

    }

    public void updateFavorites(Product productFavoriteChanged) {

        if (productFavoriteChanged.isFavorite()) {
            favNameList.add(productFavoriteChanged.getName());

        } else {
            favNameList.remove(productFavoriteChanged.getName());
        }
        firebaseFirestore.collection("users")
                .document(firebaseAuth.getCurrentUser().getUid())
                .update("favoriteProducts", favNameList)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Response response = new Response();
                            response.setResponseCode(RESPONSE_SUCCESSFUL);
                            response.setResponseFor(RESPONSE_PRODUCT_ADDED_TO_FAV);
                            if (productFavoriteChanged.isFavorite()) {
                                response.setResponseMessage("Product added to favorites");

                                favProducts.add(productFavoriteChanged);
                            } else {
                                favProducts.remove(productFavoriteChanged);
                                response.setResponseMessage("Product removed from favorites");
                                Repository.this.response.setValue(new Event<>(response));
                            }
                            Repository.this.response.setValue(new Event<>(response));
                        } else {
                            Response response = new Response();
                            response.setResponseCode(RESPONSE_FAILED);
                            response.setResponseFor(RESPONSE_PRODUCT_ADDED_TO_FAV);
                            response.setResponseMessage("Failed to add to favorites");
                            if (productFavoriteChanged.isFavorite()) {
                                favNameList.remove(productFavoriteChanged.getName());

                            } else {
                                favNameList.add(productFavoriteChanged.getName());
                            }
                            Repository.this.response.setValue(new Event<>(response));
                        }
                    }
                });

    }

    public void updateCart(Product productToUpdate, boolean addingToCart) {

        if (addingToCart) {
            cartProductsNames.add(productToUpdate.getName());
        } else {
            cartProductsNames.remove(productToUpdate.getName());
        }
        SharedPreferences sharedPreferences = application.getSharedPreferences("MY_PREFERENCES", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("PRODUCTS_IN_CART", cartProductsNames.size());
        editor.apply();

        firebaseFirestore.collection("users")
                .document(firebaseAuth.getCurrentUser().getUid())
                .update("productsInCart", cartProductsNames)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Response response = new Response();
                            response.setResponseCode(RESPONSE_SUCCESSFUL);
                            if (addingToCart) {
                                productsInCart.add(productToUpdate);
                                response.setResponseMessage("Product added to cart");
                            } else {
                                productsInCart.remove(productToUpdate);
                                response.setResponseMessage("Product removed from cart");
                            }
                            Repository.this.response.setValue(new Event<>(response));
                        } else {
                            if (addingToCart) {
                                cartProductsNames.remove(productToUpdate.getName());

                            } else {
                                cartProductsNames.add(productToUpdate.getName());

                            }
                            Response response = new Response();
                            response.setResponseCode(RESPONSE_FAILED);
                            response.setResponseMessage("Failed to add to cart, check connection");
                            Repository.this.response.setValue(new Event<>(response));
                        }
                    }
                });
    }

    public void updateUserData(HashMap<String, Object> data) {

        SharedPreferences sharedPreferences = application.getSharedPreferences("MY_PREFERENCES", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("PRODUCTS_IN_CART", cartProductsNames.size());
        editor.apply();
        firebaseFirestore.collection("users")
                .document(firebaseAuth.getCurrentUser().getUid())
                .update(data)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Response response = new Response();
                            response.setResponseCode(RESPONSE_SUCCESSFUL);
                            response.setResponseMessage("Data updated");
                            Repository.this.response.setValue(new Event<>(response));
                        } else {
                            Response response = new Response();
                            response.setResponseCode(RESPONSE_FAILED);
                            response.setResponseMessage("Failed to update data, check connection");

                            Repository.this.response.setValue(new Event<>(response));
                        }
                    }
                });
    }

    public void getUser() {
        firebaseAuth = FirebaseAuth.getInstance();

        final DocumentReference documentReference = firebaseFirestore.collection("users").document(firebaseAuth.getCurrentUser().getUid());

        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                if (value != null) {
                    User user = value.toObject(User.class);
                    userLiveData.setValue(new Event<>(user));
                    userProfileImage.setValue(user.getImage());
                }
                if (error != null) {
                    Response response = new Response();
                    response.setResponseMessage("Failed loading user details");
                    Repository.this.response.setValue(new Event<>(response));
                }

            }
        });

    }

    public LiveData<Event<Response>> getResponse() {
        return response;
    }

    public LiveData<Event<User>> getUserLiveData() {
        return userLiveData;
    }

    public LiveData<String> userProfileImage() {
        return userProfileImage;
    }

    public LiveData<List<Product>> productList() {
        return productsLiveData;
    }

    public ArrayList<Product> getFavoriteProducts() {
        return favProducts;
    }

    public List<Product> getProductsInCart() {
        return productsInCart;
    }

    public List<String> getCartProductsNames() {
        return cartProductsNames;
    }

    public LiveData<Integer> getProductUploadStatus() {
        return uploadComplete;
    }

    public LiveData<ImageUploadResponse> getImageUploadResponse() {
        return imageUploadComplete;
    }

    public ArrayList<Product> getTrendingProducts() {
        return trendingProducts;
    }

    public void setTrendingProducts(ArrayList<Product> trendingProducts) {
        this.trendingProducts = trendingProducts;
    }

    public ArrayList<Product> getElctronicProducts() {
        return elctronicProducts;
    }

    public void setElctronicProducts(ArrayList<Product> elctronicProducts) {
        this.elctronicProducts = elctronicProducts;
    }

    public ArrayList<Product> getKidProducts() {
        return kidProducts;
    }

    public void setKidProducts(ArrayList<Product> kidProducts) {
        this.kidProducts = kidProducts;
    }
}
