package com.systemtechnology.devregister.model

import com.orm.SugarRecord
import com.systemtechnology.devregister.entity.ActivityDevEntity

object ModelActivityDeveloper {

    fun getAllActivityDev() : MutableList<ActivityDevEntity>? {
        return SugarRecord.listAll(ActivityDevEntity::class.java)
    }

}
