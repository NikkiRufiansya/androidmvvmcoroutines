package com.example.androidmvvmcoroutines.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidmvvmcoroutines.models.UserModels
import com.example.androidmvvmcoroutines.repository.UserRespository
import com.google.gson.JsonObject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserViewModel constructor(private val userRespository: UserRespository) : ViewModel() {
    val errorMessage = MutableLiveData<String>()
    val userList = MutableLiveData<List<UserModels>>()
    var job: Job? = null



    val loading = MutableLiveData<Boolean>()


    fun getAllUser(){
        job = CoroutineScope(Dispatchers.IO ).launch {
            var response = userRespository.getAllUsers()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    userList.postValue(response.body()?.data)
                    loading.value = false
                } else {
                    loading.value = false
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}