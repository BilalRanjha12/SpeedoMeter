package com.innova.ecommercestore.ui.productentryactivity;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.innova.ecommercestore.data.Repository;
import com.innova.ecommercestore.models.ImageUploadResponse;
import com.innova.ecommercestore.models.Product;

import java.util.List;

public class ProductEntryViewModel extends AndroidViewModel {
    private ProductEntryViewModel productEntryViewModel;
    private final Repository repository;

    public ProductEntryViewModel(@NonNull Application application) {
        super(application);
        repository = Repository.getRepository(application);
        }

        public void createProduct(String productName, String productCategory, int price,String productDescription, List<String> images,List<String> tags){
        repository.createProduct(productName,productCategory,price,productDescription,images,tags);

        }

        public void uploadImage(Uri uri,String productCategory,String productName,int imageNo){
            repository.uploadImage(uri,productCategory,productName,imageNo);
        }
        public LiveData<List<Product>> productListChanged(){
        return repository.productList();
        }
        public  LiveData<Integer> getUploadStatus(){
        return repository.getProductUploadStatus();
        }
        public LiveData<ImageUploadResponse> getImageUploadResponse(){

        return repository.getImageUploadResponse();
        }
    }



