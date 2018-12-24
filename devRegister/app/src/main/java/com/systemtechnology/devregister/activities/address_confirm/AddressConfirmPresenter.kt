package com.systemtechnology.devregister.activities.address_confirm

import com.systemtechnology.devregister.R
import com.systemtechnology.devregister.define_rules.ActivityMethods
import com.systemtechnology.devregister.define_rules.RulesBasePresenter
import com.systemtechnology.devregister.entity.AddressEntity
import io.reactivex.Observable

class AddressConfirmPresenter(activityMethods: AddressConfirmMethods) : RulesBasePresenter(activityMethods) {

    private val acm = activityMethods

    private fun checkAddressAndIfSuccessSendBroadcast(numberHome: String, complementary: String, addressEntity: AddressEntity) {
        Observable
            .just( addressEntity )
            .map {
                addressEntity.houseNumber     = numberHome
                addressEntity.complementary   = complementary

                addressEntity.houseNumber != ""
            }.filter {
                if( !it ) {
                    acm.whenNotValid( R.string.form_cep_error_number_street )
                }
                it
            }.doAfterNext {
                acm.sendBroadcastAddress()
            }
            .subscribe()
    }

    fun whenClickedConfirm(numberHome: String, complementary: String, addressEntity: AddressEntity) {
        checkAddressAndIfSuccessSendBroadcast( numberHome , complementary , addressEntity )
    }

}

interface AddressConfirmMethods : ActivityMethods {
    fun whenNotValid(msg: Int)
    fun sendBroadcastAddress()
}