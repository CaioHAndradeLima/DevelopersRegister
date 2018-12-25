package com.systemtechnology.devregister.utils_date

import java.text.SimpleDateFormat
import java.util.*

object UtilsDateFormat {

    private const val PATTERN_1 = "dd/MM/yyyy HH:mm:ss"
    private const val PATTERN_2 = "HH:mm:ss dd/MM/yyyy"
    private const val PATTERN_3 = "HH:mm dd/MM/yyyy"


    fun format( date : Date ) : String {
        return SimpleDateFormat( PATTERN_3 )
                    .format( date )

    }
}
