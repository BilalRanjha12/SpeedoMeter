package com.innova.ecommercestore.ui.mainactivity;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.innova.ecommercestore.data.Repository;

public class MainActivityViewModel extends AndroidViewModel {

    Repository repository;
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        repository = Repository.getRepository(application);
    }

    public LiveData<String> getUserProfileImage(){
        return repository.userProfileImage();
    }


}
