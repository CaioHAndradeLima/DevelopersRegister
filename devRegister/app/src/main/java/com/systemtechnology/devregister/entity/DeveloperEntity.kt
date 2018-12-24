package com.systemtechnology.devregister.entity

import com.orm.SugarRecord

class DeveloperEntity : SugarRecord() {
    var name : String = ""
    var CPF  : String = ""
    var email: String = ""
    var password: String = ""

    lateinit var addressEntity : AddressEntity

    companion object {
        fun getAllDevelopers(): MutableList<DeveloperEntity>? {
            return listAll( DeveloperEntity::class.java )
        }
    }

    override fun save() : Long {
        addressEntity.id = super.save()
        return addressEntity.save()
    }

    fun addressIsInitialized() : Boolean {
        return ::addressEntity.isInitialized
    }


}
