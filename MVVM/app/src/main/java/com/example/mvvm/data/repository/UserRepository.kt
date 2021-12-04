package com.example.mvvm.data.repository

import com.example.mvvm.data.network.MyApi
import com.example.mvvm.data.network.SafeApiRequest
import com.example.mvvm.data.network.response.AuthResponse
import com.example.mvvm.db.AppDatabase
import com.example.mvvm.db.entites.User


class UserRepository (
    private  val api : MyApi,
    private val db : AppDatabase
        ): SafeApiRequest(){

    suspend fun userLogin(email: String,password :String) : AuthResponse {
        return apiRequest { api.userLogin(email, password) }
    }

    suspend fun saveUser(user: User) = db.getUserDao().upsert(user)

    fun getUser() = db.getUserDao().getuser()


}