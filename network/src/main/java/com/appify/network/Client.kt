package com.appify.network

import com.appify.network.models.Login
import com.appify.network.models.LoginResponse
import com.appify.network.models.Register
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterClient {
    @POST("register/")
    fun register(@Body register: Register): Call<Register>

    @POST("login/")
    fun login(@Body register: Login): Call<LoginResponse>
}
