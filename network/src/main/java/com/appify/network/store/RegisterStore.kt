package com.appify.network.store

import com.appify.network.ClientGenerator
import com.appify.network.DataCallback
import com.appify.network.RegisterClient
import com.appify.network.models.ApiError
import com.appify.network.models.Login
import com.appify.network.models.LoginResponse
import com.appify.network.models.Register
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterStore private constructor() {

    private val clientGenerator: ClientGenerator = ClientGenerator()

    fun register(register: Register, dataCallback: DataCallback<Register>) {
        val workClient = clientGenerator.createClient(RegisterClient::class.java)
        val call = workClient.register(register)
        call.enqueue(object : Callback<Register> {
            override fun onResponse(call: Call<Register>, response: Response<Register>) {
                if (response.isSuccessful && response.body() != null) {
                    dataCallback.onSuccess(response.body())
                } else {
                    dataCallback.onFailure(clientGenerator.parseError(response))
                }
            }

            override fun onFailure(call: Call<Register>, t: Throwable) {
                dataCallback.onFailure(ApiError(t.localizedMessage))
            }

        })
    }

    fun login(login: Login, dataCallback: DataCallback<LoginResponse>) {
        val workClient = clientGenerator.createClient(RegisterClient::class.java)
        val call = workClient.login(login)
        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    dataCallback.onSuccess(response.body())
                } else {
                    dataCallback.onFailure(clientGenerator.parseError(response))
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                dataCallback.onFailure(ApiError(t.localizedMessage))
            }

        })
    }

    companion object {
        val instance: RegisterStore
            get() = RegisterStore()
    }
}
