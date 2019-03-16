package com.appify.network

import android.location.Address
import com.appify.network.models.ApiError

interface DataCallback<T> {
    fun onSuccess(t: T?)

    fun onFailure(error: ApiError)
}

interface EmptyCallback {
    fun onSuccess()

    fun onFailure(error: ApiError)
}

interface AddressCallback {
    fun onSuccess(address: Address)

    fun onFailure()
}
