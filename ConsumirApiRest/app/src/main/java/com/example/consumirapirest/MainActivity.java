package com.example.consumirapirest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.consumirapirest.models.LoginModel;
import com.example.consumirapirest.models.LoginRequest;
import com.example.consumirapirest.services.LoginService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginUser();
    }

    public void loginUser(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.18.60.199/moviles2_jueves/ApiRest/features/").addConverterFactory(GsonConverterFactory.create())
                .build();
        LoginService loginService = retrofit.create(LoginService.class);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("juanzabala2193@gmail.com");
        loginRequest.setIdentification("123456789");
        Call<LoginModel> login = loginService.login(loginRequest);
        login.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                if(response.isSuccessful()){
                    LoginModel model = response.body();
                    Toast.makeText(MainActivity.this, model.getFullname(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {

            }
        });
    }
    public void listUsers(){
        ListUsersService listUsersService = retrofit.create(ListUsersService.class);
        Call<List<UserModel>> listUsers = listUsersService.listUsers();
        listUsers.enqueue(new Callback<List<UserModel>>() {
            @Override
            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                List<UserModel> listUser = response.body();
                Toast.makeText(MainActivity.this, ""+listUser.size(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<List<UserModel>> call, Throwable t) {

            }
        });
    }
}
