package com.systemtechnology.devregister.activities.activity_developer_details_dev

import com.systemtechnology.devregister.define_rules.ActivityMethods
import com.systemtechnology.devregister.define_rules.RulesBasePresenter
import com.systemtechnology.devregister.entity.ActivityDevEntity
import com.systemtechnology.devregister.entity.DeveloperEntity
import java.lang.IllegalStateException

class ActivityDevDetailsPresenter(activityMethods: ActivityMethods) : RulesBasePresenter(activityMethods) {

    companion object {

        @JvmStatic
        fun updateActivityDev( developer : DeveloperEntity , ade : ActivityDevEntity) : Int {
            for ( (index , it) in developer.listActivityDev.withIndex() ) {
                if( it.id == ade.id) {
                    it.status           = ade.status
                    it.dateToDelivery   = ade.dateToDelivery
                    it.description      = ade.description
                    return index
                }
            }

            throw IllegalStateException("not found")
        }
    }


}
