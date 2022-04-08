package com.example.consumirapirest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.consumirapirest.databinding.ActivityMainBinding;
import com.example.consumirapirest.models.UserModel;
import com.example.consumirapirest.models.LoginRequest;
import com.example.consumirapirest.services.ListUsersService;
import com.example.consumirapirest.services.LoginService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        retrofit = new Retrofit.Builder()
                .baseUrl("http://172.18.78.187/moviles2_jueves/ApiRest/features/").addConverterFactory(GsonConverterFactory.create())
                .build();
        //listUsers();
    }

    public void loginUser(){
        LoginService loginService = retrofit.create(LoginService.class);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("juanzabala2193@gmail.com");
        loginRequest.setIdentification("123456789");
        Call<UserModel> login = loginService.login(loginRequest);
        login.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if(response.isSuccessful()){
                    UserModel model = response.body();
                    Toast.makeText(MainActivity.this, model.getFullname(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {

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
                for(int i=0; i<listUser.size(); i++){
                    Log.d("sapo perro",listUser.get(i).getFullname());
                }
                Toast.makeText(MainActivity.this, ""+listUser.size(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<List<UserModel>> call, Throwable t) {

            }
        });
    }

    public void searchUser(View view){
        ListUsersService listUsersService = retrofit.create(ListUsersService.class);
        String user = binding.etFindUser.getText().toString();
        Call<UserModel> searchUser = listUsersService.searchUser(user);
        searchUser.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if(response.isSuccessful()){
                    UserModel userModel = response.body();
                    binding.tvUser.setText(userModel.getFullname());
                    binding.tvEmail.setText(userModel.getEmail());
                    Log.d("User", userModel.getFullname() + userModel.getEmail());
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {

            }
        });
    }
}
