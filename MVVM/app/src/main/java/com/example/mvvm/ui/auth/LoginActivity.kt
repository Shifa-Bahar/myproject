package com.example.mvvm.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mvvm.R
import com.example.mvvm.data.network.MyApi
import com.example.mvvm.data.repository.UserRepository
import com.example.mvvm.databinding.ActivityLoginBinding
import com.example.mvvm.db.AppDatabase
import com.example.mvvm.db.entites.User
import com.example.mvvm.ui.home.HomeActivity
import com.example.mvvm.util.toast

class LoginActivity : AppCompatActivity(),AuthListner {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val api = MyApi()
        val db = AppDatabase(this)
        val repository = UserRepository(api,db)
        val factory = AuthViewModelFactory(repository)

       val binding : ActivityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this,factory).get(AuthViewModel::class.java)
        viewModel.getLoggedInUser().observe(this , Observer {user ->
            if(user != null){
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }

            }
        })

        binding.viewmodel = viewModel
        viewModel.authListner = this
    }

    override fun onStarted() {
     toast("login started")

    }

    override fun onSucess(user: User) {
//    toast("${user.username} is logged in")

    }

    override fun onFailure(message: String) {
        toast(message)

    }
}