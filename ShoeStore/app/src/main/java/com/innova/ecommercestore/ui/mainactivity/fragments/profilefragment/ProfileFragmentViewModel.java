package com.innova.ecommercestore.ui.mainactivity.fragments.profilefragment;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.innova.ecommercestore.data.Repository;
import com.innova.ecommercestore.models.Response;
import com.innova.ecommercestore.models.User;
import com.innova.ecommercestore.utils.Event;

import java.util.HashMap;

public class ProfileFragmentViewModel extends AndroidViewModel {

    private Repository repository;
    public ProfileFragmentViewModel(@NonNull Application application) {
        super(application);
        repository = Repository.getRepository(application);
    }

    public void updateUserData(HashMap<String,Object> data){

        repository.updateUserData(data);
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

    public void uploadProfileImage(Uri profileUri){

        repository.uploadProfileImage(profileUri);
    }

}
