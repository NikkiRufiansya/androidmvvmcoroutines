package com.example.androidmvvmcoroutines.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidmvvmcoroutines.repository.UserRespository

class UserViewModelFactory constructor(private val userRespository: UserRespository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(UserViewModel::class.java)){
            UserViewModel(this.userRespository) as T
        }else{
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}