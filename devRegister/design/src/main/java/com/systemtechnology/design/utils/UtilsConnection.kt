package com.systemtechnology.design.utils

import android.content.Context
import android.net.ConnectivityManager


object UtilsConnection {


    fun isConnected(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager!!.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    fun isNotConnected( context: Context ) : Boolean {
        return !isConnected(context)
    }
}
