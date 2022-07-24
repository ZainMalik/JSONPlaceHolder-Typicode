package com.zain.emaartest.Repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import com.zain.emaartest.DAO.UserDao;
import com.zain.emaartest.Database.UserDatabase;
import com.zain.emaartest.Model.User;
import com.zain.emaartest.MyApplication;
import com.zain.emaartest.Network.Retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRespository {

    private UserDatabase database;
    private LiveData<List<User>> getAllUsers;

    public UserRespository(Application application) {
        database = UserDatabase.getInstance(application);
        getAllUsers = database.userDao().getAllUsers();
    }

    public void insert(List<User> userList){
        new InsertAsyncTask(database).execute(userList);
    }

    public LiveData<List<User>> getAllUsers() {
        return getAllUsers;
    }

    static class InsertAsyncTask extends AsyncTask<List<User>,Void,Void>{
        private UserDao userDao;
        InsertAsyncTask(UserDatabase userDatabase) {
             userDao = userDatabase.userDao();
        }
        @Override
        protected Void doInBackground(List<User>... lists) {
            userDao.insert(lists[0]);
            return null;
        }
    }

    public void apiCallUserList() {
        Retrofit retrofit = new Retrofit();
        Call<List<User>> call = retrofit.api.getAllUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.isSuccessful()) {
                    insert(response.body());
                    Log.d("MainActivity", "onResponse: " + response.body());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(MyApplication.getContext(), "Something Went Wrong...", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
