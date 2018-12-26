package com.systemtechnology.devregister.activities.register_activity

import com.systemtechnology.devregister.define_rules.ActivityMethods
import com.systemtechnology.devregister.define_rules.RulesBasePresenter
import com.systemtechnology.devregister.entity.ActivityDevEntity
import com.systemtechnology.devregister.entity.DeveloperEntity
import com.systemtechnology.devregister.utils_date.UtilsDate
import com.systemtechnology.devregister.utils_date.UtilsDateFormat
import com.systemtechnology.devregister.validate_helper.ActivityDevEntityValidate

class ActivityRegisterPresenter(private val arm: ActivityRegisterMethods) : RulesBasePresenter( arm ) {

    private lateinit var activityDevEntity : ActivityDevEntity

    //is inserting or updating
    private var isInserting = true

    override fun onCreate() {
        super.onCreate()

        val actEntity = arm.getActivityDevIfExists()
        val developerEntity = arm.getDeveloperEntity()

        isInserting = actEntity == null

        if( isInserting ) {
            activityDevEntity = ActivityDevEntity()
            activityDevEntity.requested = developerEntity.CPF
            activityDevEntity.dateRegistered = UtilsDateFormat.format( UtilsDate.getToday() )

            arm.notifyInserting()
        } else {
            activityDevEntity = actEntity!!
            arm.notifyUpdating( activityDevEntity )
        }

    }

    fun onClick(requester: String, description: String, dateSelected: String?) {
        if( isInserting ) {
            activityDevEntity.requester = requester
            activityDevEntity.description = description

            if( dateSelected != null ) {
                activityDevEntity.dateToDelivery = dateSelected
            }

            val resString = ActivityDevEntityValidate.validate( activityDevEntity )

            if( resString != 0 ) {
                arm.whenFormError( resString )
            } else {
                activityDevEntity.save()
                arm.whenInsertedDevEntity( activityDevEntity )
            }

        }
    }

}


interface ActivityRegisterMethods : ActivityMethods {

    fun whenFormError( resString : Int )
    fun whenInsertedDevEntity( ade : ActivityDevEntity )

    fun getActivityDevIfExists() : ActivityDevEntity?

    fun notifyUpdating(activityDevEntity : ActivityDevEntity)
    fun notifyInserting()

    fun getDeveloperEntity(): DeveloperEntity

}