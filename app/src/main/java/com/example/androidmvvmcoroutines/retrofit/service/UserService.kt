package com.example.androidmvvmcoroutines.retrofit.service

import com.example.androidmvvmcoroutines.models.EmployeModels
import com.example.androidmvvmcoroutines.models.UserModels
import com.example.androidmvvmcoroutines.retrofit.network.RetrofitClient
import retrofit2.Response
import retrofit2.http.GET

interface UserService {
    @GET("users?page")
    suspend fun getAllUsers() : Response<EmployeModels>

    companion object{
        fun create(): UserService{
            return RetrofitClient.getInstance().create(UserService::class.java)
        }
    }
}