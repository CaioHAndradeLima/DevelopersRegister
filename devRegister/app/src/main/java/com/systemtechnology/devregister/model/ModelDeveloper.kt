package com.systemtechnology.devregister.model

import com.systemtechnology.devregister.define_rules.RulesBaseModel
import com.systemtechnology.devregister.entity.Address
import com.systemtechnology.devregister.entity.Developer

class ModelDeveloper : RulesBaseModel() {

    fun getAllDevelopers() : MutableList<Developer>? {
        val list = Developer.getAllDevelopers() ?: return null

        val listAddress = Address.getAllAddress()!!

        list.forEach { client ->

            for( address in listAddress){
                if(address.id == client.id) {
                    client.address = address
                    continue
                }
            }
        }

        return list
    }
}
