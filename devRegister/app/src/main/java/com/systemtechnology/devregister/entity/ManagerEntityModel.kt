package com.systemtechnology.devregister.entity

import com.orm.SugarRecord

class ManagerEntityModel {

    companion object {

        @JvmStatic
        fun search() : MutableList<ActivityDevEntity>? {
            return SugarRecord.listAll(ActivityDevEntity::class.java)
        }

    }

}
