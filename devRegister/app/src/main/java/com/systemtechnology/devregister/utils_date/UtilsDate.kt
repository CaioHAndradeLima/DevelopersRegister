package com.systemtechnology.devregister.utils_date

import java.util.*

object UtilsDate {

    fun getToday() : Date {
        return Date()
    }

    fun getDateAfterDaysPassedByParam( days : Int , dateInit : Date = getToday() ) : Date {
        return Date(dateInit.time + ((ONE_DAY_IN_TIMEMILLS) * days ))
    }

    const val ONE_DAY_IN_TIMEMILLS = 1000 * 60 * 60 * 24

}
