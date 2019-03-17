package com.appify.network

import com.appify.network.models.Login
import com.appify.network.models.LoginResponse
import com.appify.network.models.Register
import com.appify.network.models.Todo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface RegisterClient {
    @POST("api/v1/user_todo/register/")
    fun register(@Body register: Register): Call<Register>

    @POST("api/v1/user_todo/login/")
    fun login(@Body register: Login): Call<LoginResponse>
}

interface ToDoClient {
    @POST("api/v1/todo/create/")
    fun create(@Body create: Todo, @Header("Authorization") token: String): Call<Todo>

    @POST("api/v1/user_todo/update/")
    fun update(@Body update: Todo, @Header("Authorization") token: String): Call<Todo>

    @GET("api/v1/user_todo/get/")
    fun getNewTask(@Body getNewTask: Todo, @Header("Authorization") token: String): Call<Todo>
}
