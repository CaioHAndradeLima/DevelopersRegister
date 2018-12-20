package com.systemtechnology.devregister.utils

import com.google.gson.Gson

object UtilsConvertJson {

    fun toJson(obj : Any) : String {
        return Gson().toJson( obj )
    }

    fun <T>fromJson( json : String?, obj : Class<T> ) : T{
        return Gson().fromJson( json , obj )
    }

}
