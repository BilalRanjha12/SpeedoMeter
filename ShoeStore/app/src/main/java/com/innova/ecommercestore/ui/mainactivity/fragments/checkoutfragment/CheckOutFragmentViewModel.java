package com.innova.ecommercestore.ui.mainactivity.fragments.checkoutfragment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.innova.ecommercestore.models.Response;
import com.innova.ecommercestore.models.User;
import com.innova.ecommercestore.utils.Event;
import com.innova.ecommercestore.data.Repository;
import com.innova.ecommercestore.models.Product;

import java.util.List;

public class CheckOutFragmentViewModel extends AndroidViewModel {

    private final Repository repository;

    private MutableLiveData<Event<List<Product>>> products=new MutableLiveData<>();


    public CheckOutFragmentViewModel(@NonNull Application application) {
        super(application);
        repository = Repository.getRepository(application);
    }

    public List<Product> getProductsInCart(){
        return repository.getProductsInCart();
    }



    public void updateCart(Product product,boolean addingToCart) {

        repository.updateCart(product,addingToCart);
    }

    public LiveData<Event<Response>> getResponse(){
        return repository.getResponse();
    }


    public void getUser(){
        repository.getUser();
    }

    public LiveData<Event<User>> getUserLiveData(){
        return repository.getUserLiveData();
    }

    public LiveData<String> getUserProfileImage(){
        return repository.userProfileImage();
    }
}
