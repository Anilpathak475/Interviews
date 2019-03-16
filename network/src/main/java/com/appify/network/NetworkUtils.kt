package com.appify.network


import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by anilpathak on 05/09/17.
 */
object NetworkUtils {

    @SuppressLint("MissingPermission")
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo

        return networkInfo != null && networkInfo.isConnected
    }
}


