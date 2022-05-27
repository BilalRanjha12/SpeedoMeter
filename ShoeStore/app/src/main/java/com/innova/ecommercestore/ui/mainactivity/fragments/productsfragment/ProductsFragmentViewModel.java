package com.innova.ecommercestore.ui.mainactivity.fragments.productsfragment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.innova.ecommercestore.utils.Event;
import com.innova.ecommercestore.data.Repository;
import com.innova.ecommercestore.models.Product;
import com.innova.ecommercestore.models.Response;

import java.util.ArrayList;
import java.util.List;

public class ProductsFragmentViewModel extends AndroidViewModel {

    private final Repository repository;

    public ProductsFragmentViewModel(@NonNull Application application) {
        super(application);
        repository = Repository.getRepository(application);
    }
    public void getProducts(){
        repository.getProducts();
    }
    public ArrayList<Product> getTrendingProducts(){
        repository.getTrending();
        return repository.getTrendingProducts();
    }

    public ArrayList<Product> getFavoriteProducts(){
        return repository.getFavoriteProducts();
    }

    public ArrayList<Product> getkidsProducts(){
        repository.getKidsProducts();
        return repository.getKidProducts();
    }
    public ArrayList<Product> getElectronics(){
        repository.getElectronics();
        return repository.getElctronicProducts();
    }
    public void getFavAndCartProducts() {
        repository.getFavAndCartProducts();
    }

    public LiveData<List<Product>> products(){
        return repository.productList();
    }

    public void updateFavorites(Product productFavoriteChanged){
        repository.updateFavorites(productFavoriteChanged);

    }
    public LiveData<Event<Response>> getResponse(){
        return repository.getResponse();
    }

    public void getUser(){
        repository.getUser();
    }

    public LiveData<String> getUserProfileImage(){
        return repository.userProfileImage();
    }

}
