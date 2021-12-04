package com.example.mvvm.ui.auth

import com.example.mvvm.db.entites.User

interface AuthListner {
    fun onStarted()
    fun onSucess(user: User)
    fun onFailure(message: String)
}