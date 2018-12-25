package com.systemtechnology.devregister.model

import com.orm.SugarRecord
import com.systemtechnology.devregister.define_rules.RulesBaseModel
import com.systemtechnology.devregister.entity.ActivityDevEntity
import com.systemtechnology.devregister.entity.AddressEntity
import com.systemtechnology.devregister.entity.DeveloperEntity

class ModelDeveloper : RulesBaseModel() {

    fun getAllDevelopers() : MutableList<DeveloperEntity>? {
        val list = SugarRecord.listAll( DeveloperEntity::class.java ) ?: return null

        val listAddress = SugarRecord.listAll( AddressEntity::class.java )

        val listActivity = SugarRecord.listAll( ActivityDevEntity::class.java )


        list.forEach { dev ->

            for( address in listAddress){
                if(address.id == dev.id) {
                    dev.addressEntity = address
                    break
                }
            }
        }


        list.forEach { dev ->


            listActivity.forEach {
                if( dev.thisActivityBelongsThisDev( it ) ) {
                    dev.addActivityDev( it )
                }
            }
        }

        return list
    }
}
