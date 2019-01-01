package com.systemtechnology.devregister.messages_layout_entity

import android.content.res.Resources
import com.systemtechnology.devregister.R
import com.systemtechnology.devregister.entity.ActivityDevEntity
import com.systemtechnology.devregister.entity.infix_methods.*
import java.lang.IllegalStateException

object ActivityDevMessages {

    fun getMessageStatus( ad : ActivityDevEntity ) : Int {
        if( ad.isStopped() ) {
            return R.string.activity_dev_status_stopped
        } else if(ad.isDelivered()) {
            return R.string.activity_dev_status_delivered
        } else if(ad.isExecuting()) {
            return R.string.activity_dev_status_executing
        } else {
            throw IllegalStateException("status not implemented ${ad.status.name}")
        }
    }

    fun getMessageDeliveryDate( ad : ActivityDevEntity ,
                                res : Resources ) : String {

        if( ad.isDelivered() ) {
            return "${res.getString(R.string.activity_dev_delivered_date_start_msg)} ${ad.dateToDelivery}"
        } else if ( ad.isExecuting() || ad.isStopped() ) {
            return "${res.getString(R.string.activity_dev_delivery_prevision)} ${ad.dateToDelivery}"
        } else {
            throw IllegalStateException("status not implemented ${ad.status.name}")
        }
    }

    fun getMessageRequester(ad : ActivityDevEntity ,
                            res : Resources) : String {
        return "${res.getString(R.string.activity_dev_requester)}  ${ad.requester}"
    }

    fun getImageResource(activityDev: ActivityDevEntity): Int {
        return if( activityDev.isStopped() ) {
            R.drawable.ic_activity_stopped
        } else if ( activityDev.isExecuting() ) {
            R.drawable.ic_activity_executing
        } else if( activityDev.isDelivered() ) {
            R.drawable.ic_activity_done
        } else {
            throw IllegalStateException("not implemented yet ${activityDev.status}")
        }
    }

}
