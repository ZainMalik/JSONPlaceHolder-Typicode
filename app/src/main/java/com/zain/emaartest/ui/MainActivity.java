package com.zain.emaartest.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.zain.emaartest.Adapter.UserAdapter;
import com.zain.emaartest.Model.User;
import com.zain.emaartest.R;
import com.zain.emaartest.Repository.UserRespository;
import com.zain.emaartest.ViewModel.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private UserViewModel userViewModel;
    private List<User> userList, userListEven;
    private UserRespository userRespository;
    private UserAdapter userAdapter;
    private RecyclerView rvUsers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvUsers = findViewById(R.id.rv_users);
        rvUsers.setHasFixedSize(true);
        rvUsers.setLayoutManager(new LinearLayoutManager(this));
        rvUsers.setItemAnimator(new DefaultItemAnimator());

        userList = new ArrayList<>();
        userListEven = new ArrayList<>();
        userRespository = new UserRespository(getApplication());
        userAdapter = new UserAdapter(this, userList);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        userRespository.apiCallUserList();
        
        userViewModel.getAllUser().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> userList) {
                rvUsers.setAdapter(userAdapter);
                /** Even Positions for RecyclerView **/
                for(int i = 0; i < userList.size(); i++){
                    if(i % 2 == 0){
                        userListEven.add(userList.get(i));
                    }
                }
                userAdapter.getAllUsers(userListEven);

                Log.d("MainActivity", "onChanged: " + userList);
            }
        });

    }

}
