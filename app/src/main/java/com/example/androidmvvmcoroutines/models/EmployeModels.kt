package com.example.androidmvvmcoroutines.models

data class EmployeModels (val page: Int, val per_page:Int, val total : Int, val total_pages: Int, val data: List<UserModels>)