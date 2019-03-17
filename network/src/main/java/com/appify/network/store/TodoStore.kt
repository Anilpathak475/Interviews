package com.appify.network.store

import com.appify.network.ClientGenerator
import com.appify.network.DataCallback
import com.appify.network.ToDoClient
import com.appify.network.models.ApiError
import com.appify.network.models.Todo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TodoStore private constructor() {

    private val clientGenerator: ClientGenerator = ClientGenerator()

    fun create(todo: Todo, token: String, dataCallback: DataCallback<Todo>) {
        val workClient = clientGenerator.createClient(ToDoClient::class.java)
        val call = workClient.create(todo, token)
        call.enqueue(object : Callback<Todo> {
            override fun onResponse(call: Call<Todo>, response: Response<Todo>) {
                if (response.isSuccessful && response.body() != null) {
                    dataCallback.onSuccess(response.body())
                } else {
                    dataCallback.onFailure(clientGenerator.parseError(response))
                }
            }

            override fun onFailure(call: Call<Todo>, t: Throwable) {
                dataCallback.onFailure(ApiError(t.localizedMessage))
            }

        })
    }

    fun update(todo: Todo, token: String, dataCallback: DataCallback<Todo>) {
        val workClient = clientGenerator.createClient(ToDoClient::class.java)
        val call = workClient.update(todo, token)
        call.enqueue(object : Callback<Todo> {
            override fun onResponse(call: Call<Todo>, response: Response<Todo>) {
                if (response.isSuccessful && response.body() != null) {
                    dataCallback.onSuccess(response.body())
                } else {
                    dataCallback.onFailure(clientGenerator.parseError(response))
                }
            }

            override fun onFailure(call: Call<Todo>, t: Throwable) {
                dataCallback.onFailure(ApiError(t.localizedMessage))
            }

        })
    }

    fun get(token: String, date: String, dataCallback: DataCallback<List<Todo>>) {
        val workClient = clientGenerator.createClient(ToDoClient::class.java)
        val call = workClient.get(date, token)
        call.enqueue(object : Callback<List<Todo>> {
            override fun onResponse(call: Call<List<Todo>>, response: Response<List<Todo>>) {
                if (response.isSuccessful && response.body() != null) {
                    dataCallback.onSuccess(response.body())
                } else {
                    dataCallback.onFailure(clientGenerator.parseError(response))
                }
            }

            override fun onFailure(call: Call<List<Todo>>, t: Throwable) {
                dataCallback.onFailure(ApiError(t.localizedMessage))
            }

        })
    }

    companion object {
        val instance: TodoStore
            get() = TodoStore()
    }
}
