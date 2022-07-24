package com.zain.emaartest.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.zain.emaartest.Model.User;
import com.zain.emaartest.Repository.UserRespository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private UserRespository userRespository;
    private LiveData<List<User>> getAllUsers;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRespository = new UserRespository(application);
        getAllUsers = userRespository.getAllUsers();
    }

    public void insert(List<User> list) {
        userRespository.insert(list);
    }

    public LiveData<List<User>> getAllUser() {
        return getAllUsers;
    }

}
