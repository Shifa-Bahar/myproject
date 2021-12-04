package com.example.mvvm.data.network

import com.example.mvvm.data.network.response.AuthResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MyApi {
    @FormUrlEncoded
    @POST("login.php")
     suspend fun userLogin(
        @Field("email") username: String,
        @Field("password") password: String
    ) : Response<AuthResponse>

    companion object{
        operator fun invoke() : MyApi{
            return Retrofit.Builder()
                .baseUrl("http://192.168.1.143/UserApi/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}