package com.systemtechnology.devregister.entity

import com.orm.SugarRecord

class DeveloperEntity : SugarRecord() {
    var name : String = ""
    var CPF  : String = ""
    var email: String = ""
    var password: String = ""

    lateinit var addressEntity : AddressEntity

    val listActivityDev = mutableListOf<ActivityDevEntity>()

    override fun save() : Long {
        addressEntity.id = super.save()
        return addressEntity.save()
    }

    fun addressIsInitialized() : Boolean {
        return ::addressEntity.isInitialized
    }

    fun addActivityDev( ade : ActivityDevEntity) {
        listActivityDev.add(  0 , ade )
    }

    fun thisActivityBelongsThisDev( ade : ActivityDevEntity ) : Boolean {
        return ade.requested == CPF
    }

    fun quantityActivityDevExecuting(): Any {
        return getQuantityActivityDevForThisStatus(
                    ActivityDevEntity.ActivityStatus.EXECUTING
                )
    }

    private fun getQuantityActivityDevForThisStatus( status : ActivityDevEntity.ActivityStatus) : Int {
        var quantity = 0
        listActivityDev.forEach { if( it.status == status ) quantity++ }
        return quantity
    }


}
