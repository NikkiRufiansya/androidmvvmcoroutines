package com.example.androidmvvmcoroutines.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidmvvmcoroutines.R
import com.example.androidmvvmcoroutines.adapters.UserAdapters
import com.example.androidmvvmcoroutines.databinding.ActivityListUsersBinding
import com.example.androidmvvmcoroutines.models.UserModels
import com.example.androidmvvmcoroutines.repository.UserRespository
import com.example.androidmvvmcoroutines.retrofit.service.UserService
import com.example.androidmvvmcoroutines.viewmodel.UserViewModel
import com.example.androidmvvmcoroutines.viewmodel.UserViewModelFactory
import kotlin.math.log

class ListUsersActivity : AppCompatActivity() {
    lateinit var binding: ActivityListUsersBinding
    lateinit var userViewModel: UserViewModel
    lateinit var userService: UserService
    lateinit var userRespository: UserRespository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userService = UserService.create()
        userRespository = UserRespository(userService)


        userViewModel = ViewModelProvider(
            this,
            UserViewModelFactory(userRespository)
        )[UserViewModel::class.java]


        userViewModel.userList.observe(this) {
            binding.recyclerview.apply {
                var userModels: ArrayList<UserModels> = ArrayList()
                it.forEach {
                    userModels.add(it)
                }
                val adapters = UserAdapters(userModels)
                layoutManager = LinearLayoutManager(this@ListUsersActivity)
                adapter = adapters

            }
        }

        userViewModel.errorMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        userViewModel.loading.observe(this, Observer {
            if (it) {
                binding.progressDialog.visibility = View.VISIBLE
            } else {
                binding.progressDialog.visibility = View.GONE
            }
        })

        userViewModel.getAllUser()
    }






}