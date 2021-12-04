package com.example.mvvm.data.network.response

import com.example.mvvm.db.entites.User


data class AuthResponse(
    val isSuccessful : Boolean?,
    val message: String?,
    val user: User?
)