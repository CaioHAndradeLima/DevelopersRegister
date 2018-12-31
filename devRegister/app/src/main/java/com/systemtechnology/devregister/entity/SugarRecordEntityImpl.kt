package com.systemtechnology.devregister.entity

import com.orm.SugarRecord
import com.systemtechnology.devregister.utils.UtilsConvertJson

fun SugarRecord.toJson(): String {
    return UtilsConvertJson.toJson( this )
}
