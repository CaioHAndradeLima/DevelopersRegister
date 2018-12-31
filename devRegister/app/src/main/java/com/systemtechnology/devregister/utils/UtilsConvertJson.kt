package com.systemtechnology.devregister.utils

import com.google.gson.Gson

object UtilsConvertJson {

    fun toJson(obj : Any) : String {
        return Gson().toJson( obj )
    }

    inline fun <reified  T>fromJson( json : String? ) : T{
        return Gson().fromJson( json , T::class.java )
    }

}
