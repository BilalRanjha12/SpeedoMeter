package com.innova.ecommercestore.ui.mainactivity.fragments.availablecardsfragment;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;

import com.innova.ecommercestore.data.Repository;
import com.innova.ecommercestore.ui.mainactivity.MainActivity;

public class AvailableCardsViewModel extends AndroidViewModel {
    private Repository repository;
    public AvailableCardsViewModel(@NonNull Application application) {
        super(application);
        repository = Repository.getRepository(application);
    }

    public void getUser(){
        repository.getUser();
    }

    public void emptycart(Context fragment)
    {
        repository.emptyCart(fragment);
    }

    public void updateCartItemsinSharedPrefs()
    {
        SharedPreferences sharedPreferences= getApplication().getSharedPreferences("MY_PREFERENCES", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("PRODUCTS_IN_CART",0);
        editor.apply();
    }

}
