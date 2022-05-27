package com.innova.ecommercestore.ui.mainactivity.fragments.deliverydetailsfragment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.innova.ecommercestore.data.Repository;
import com.innova.ecommercestore.models.User;
import com.innova.ecommercestore.utils.Event;

public class DeliveryDetailsViewModel extends AndroidViewModel {

    private Repository repository;
    public DeliveryDetailsViewModel(@NonNull Application application) {
        super(application);
        repository = Repository.getRepository(application);
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
