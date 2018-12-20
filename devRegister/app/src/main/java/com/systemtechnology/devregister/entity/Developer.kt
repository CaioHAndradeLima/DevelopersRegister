package com.systemtechnology.devregister.entity

import com.orm.SugarRecord

class Developer : SugarRecord() {
    var name : String = ""
    var CPF  : String = ""

    lateinit var address : Address

    companion object {
        fun getAllDevelopers(): MutableList<Developer>? {
            return listAll( Developer::class.java )
        }
    }

    override fun save(): Long {
        address.id = super.save()
        return address.save()
    }

    fun addressIsInitialized() : Boolean {
        return ::address.isInitialized
    }


}
