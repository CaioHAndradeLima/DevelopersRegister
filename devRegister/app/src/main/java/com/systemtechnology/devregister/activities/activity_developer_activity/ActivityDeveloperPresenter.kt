package com.systemtechnology.devregister.activities.activity_developer_activity

import com.systemtechnology.devregister.define_rules.ActivityMethods
import com.systemtechnology.devregister.define_rules.RulesBasePresenter
import com.systemtechnology.devregister.entity.ActivityDevEntity
import com.systemtechnology.devregister.model.ModelActivityDeveloper

class ActivityDeveloperPresenter(private val am: ActivityDeveloper) : RulesBasePresenter(am) {

    private val listActivityDev = ModelActivityDeveloper.getAllActivityDev()

    override fun onCreate() {
        super.onCreate()
        if( listActivityDev != null &&
            listActivityDev.isNotEmpty() ) {

            am.notifyListArrived(listActivityDev)

        } else {
            am.notifyNoneActivityDevYet()
        }
    }
}

interface ActivityDeveloper : ActivityMethods {
    fun notifyNoneActivityDevYet()
    fun notifyListArrived(list : MutableList<ActivityDevEntity>)
}