package com.example.farm.controllers;
import com.example.farm.model.FarmRequest;
import com.example.farm.model.LoginRequest;
import com.example.farm.model.PasswordResetRequest;
import com.example.farm.model.PlotModel;
import com.example.farm.model.RegistrationRequest;
import com.example.farm.model.UpdateProfileRequest;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {
    @POST("registration") // Replace with your actual endpoint
    Call<YourResponseClass> registerUser(@Body RegistrationRequest request);

    @POST("login")
    Call<UserResponse> authenticateUser(@Body LoginRequest loginRequest);

    @GET("checkphone/{phone}")
    Call<CheckPhoneResponse> checkPhoneNumber(@Path("phone") String phone);

    @PUT("myfarm.php/forgetpass/{user_id}")
    Call<ResponseBody> resetPassword(@Path("user_id") String userId, @Body PasswordResetRequest request);

    @PUT("updateprofile/{user_id}")
    Call<Void> updateProfile(@Path("user_id") long userId, @Body UpdateProfileRequest updateRequest);

    @POST("addfarm")
    Call<YourResponseClass> addFarm(@Body FarmRequest farmRequest);
//
//    @GET("get_farms.php")
//    Call<List<FarmPlotResponse>> getFarms(@Query("user_id") String userId);
//
//    @GET("getfarms/{userId}")
//    Call<Map<String, List<PlotModel>>> getFarms(@Path("userId") String userId);


    @GET("myfarm.php/getfarms/{userId}")
    Call<List<PlotModel>> getPlotsApiCall(@Path("userId") String userId);
}
