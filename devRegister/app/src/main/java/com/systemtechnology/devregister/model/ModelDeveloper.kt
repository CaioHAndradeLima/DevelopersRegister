package com.systemtechnology.devregister.model

import com.systemtechnology.devregister.define_rules.RulesBaseModel
import com.systemtechnology.devregister.entity.AddressEntity
import com.systemtechnology.devregister.entity.DeveloperEntity

class ModelDeveloper : RulesBaseModel() {

    fun getAllDevelopers() : MutableList<DeveloperEntity>? {
        val list = DeveloperEntity.getAllDevelopers() ?: return null

        val listAddress = AddressEntity.getAllAddress()!!

        list.forEach { client ->

            for( address in listAddress){
                if(address.id == client.id) {
                    client.addressEntity = address
                    continue
                }
            }
        }

        return list
    }
}
