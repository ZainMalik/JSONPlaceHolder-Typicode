package com.zain.emaartest.Network;

import com.zain.emaartest.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {
    @GET("posts")
    Call<List<User>> getAllUsers();
}
