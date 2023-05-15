package com.example.androidmvvmcoroutines.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidmvvmcoroutines.databinding.LayoutListUserBinding
import com.example.androidmvvmcoroutines.models.UserModels
import com.squareup.picasso.Picasso

class UserAdapters(private var userModels: List<UserModels>) : RecyclerView.Adapter<UserAdapters.ViewHolder>() {
   inner class ViewHolder(val binding: LayoutListUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LayoutListUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return userModels.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(userModels[position]){
                Log.d("TAG", "onBindViewHolder: " + userModels)
                binding.tvName.text = this.first_name
                binding.tvEmail.text = this.email
                Picasso.get().load(this.avatar).into(binding.profileImage)
            }
        }
    }


}