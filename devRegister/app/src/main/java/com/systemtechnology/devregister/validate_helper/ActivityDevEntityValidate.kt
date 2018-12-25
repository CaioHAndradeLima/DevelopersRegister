package com.systemtechnology.devregister.validate_helper

import com.systemtechnology.devregister.R
import com.systemtechnology.devregister.entity.ActivityDevEntity

object ActivityDevEntityValidate {

    fun validate( ae : ActivityDevEntity ) : Int {

        if( ae.description.length < 10 ) {
            return R.string.activity_dev_error_form_description_length
        }


        if( ae.dateToDelivery.isEmpty() ) {
            return R.string.activity_dev_error_form_date
        }

        if( ae.requester.isEmpty() ) {
            return R.string.activity_dev_error_requester
        }

        return 0
    }

}
