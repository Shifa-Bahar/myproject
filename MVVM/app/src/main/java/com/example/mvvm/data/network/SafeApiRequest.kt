package com.example.mvvm.data.network

import com.example.mvvm.util.ApiException
import okhttp3.Response
import org.json.JSONException
import org.json.JSONObject

abstract class SafeApiRequest {

    suspend fun<T: Any> apiRequest(call: suspend () -> retrofit2.Response<T>) : T{
        val response = call.invoke()
        if(response.isSuccessful){
//            if response is sucess
            return response.body()!!
        }else{
//            if error ,need to check error type
            val error = response.errorBody()?.string()
            val message = StringBuilder()
            error?.let{
                try{
                    message.append(JSONObject(it).getString("message"))
                }catch(e: JSONException){ }
                message.append("\n")
            }
            message.append("Error Code: ${response.code()}")
            throw ApiException(message.toString())
        }
    }

}