package com.example.consumirapirest.services;

import com.example.consumirapirest.models.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ListUsersService {

    @GET("list-users/list-users.php")
    Call<List<UserModel>> listUsers();
}
