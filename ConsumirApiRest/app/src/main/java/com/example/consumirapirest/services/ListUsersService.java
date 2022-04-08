package com.example.consumirapirest.services;

import com.example.consumirapirest.models.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ListUsersService {

    @GET("list-users/list-users.php")
    Call<List<UserModel>> listUsers();

    @GET("list-users/list-users.php")
    Call<UserModel> searchUser(@Query("search") String user);
}
