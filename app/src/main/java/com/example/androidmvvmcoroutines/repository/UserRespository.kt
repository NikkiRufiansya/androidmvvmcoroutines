package com.example.androidmvvmcoroutines.repository

import com.example.androidmvvmcoroutines.retrofit.service.UserService

class UserRespository constructor(private val userService: UserService){
    suspend fun getAllUsers() = userService.getAllUsers()
}