package com.example.mvvm.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.mvvm.data.repository.UserRepository
import com.example.mvvm.util.ApiException
import com.example.mvvm.util.Coroutines

class AuthViewModel(
    private val repository: UserRepository
): ViewModel() {
    var email: String? = null
    var password: String? = null
    var authListner : AuthListner? = null
    fun getLoggedInUser() = repository.getUser()

    fun onLoginButtonClick(view: View){
        authListner?.onStarted()
        if(email.isNullOrBlank() || password.isNullOrBlank()){
         authListner?.onFailure("invalid email/password")
        }
         //onSucess
        //userrepository is returing response which is livedata that we can observe in our view from here ie viewmodel
//        var loginResponse = UserRepository().userLogin(email!!,password!!)
//        authListner?.onSucess(loginResponse)


        Coroutines.main {
            try {
                val authresponse = repository.userLogin(email!!,password!!)
                authresponse.user?.let {
                    authListner?.onSucess(it)
                    repository.saveUser(it)
                    return@main
                }
            }catch (e : ApiException){
                e.message?.let { authListner?.onFailure(it) }

            }

//            here we don wanna check for sucess or fail ,we just want info if yes or no

//            if(response.isSuccessful){
//                authListner?.onSucess(response.body()?.user!!)
//            }else{
//                authListner?.onFailure("Error ${response.code()} ")
//            }

        }
    }
}