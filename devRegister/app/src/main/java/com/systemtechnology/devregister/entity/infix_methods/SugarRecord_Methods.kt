package com.systemtechnology.devregister.entity.infix_methods

import com.orm.SugarRecord
import com.systemtechnology.devregister.utils.UtilsConvertJson

fun SugarRecord.toJson(): String {
    return UtilsConvertJson.toJson( this )
}

//don't saved in data base yet
fun SugarRecord?.notSavedYet() : Boolean {
    return this == null || id == 0L
}

fun SugarRecord?.alreadySaved(): Boolean {
    return !notSavedYet()
}
