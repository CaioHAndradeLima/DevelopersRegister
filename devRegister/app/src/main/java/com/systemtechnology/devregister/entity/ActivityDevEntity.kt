package com.systemtechnology.devregister.entity

import com.orm.SugarRecord
import java.lang.IllegalStateException

data class ActivityDevEntity(
                var description : String = "" ,
                var requester   : String = "",
                var requested   : String = "", //save the cpf of developer
                var dateToDelivery : String = "",
                var dateRegistered : String = ""
                        ) : SugarRecord() {
    var status = ActivityStatus.STOPPED

    set(value) {

        if( status == ActivityStatus.STOPPED &&
            value == ActivityStatus.DELIVERED ) {
            throw IllegalStateException("delivering without execute before?")
        }

        field = value
    }


    enum class ActivityStatus {
        STOPPED, DELIVERED , EXECUTING, DELETED
    }
}
