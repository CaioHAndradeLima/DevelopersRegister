package com.systemtechnology.devregister.activities.update_or_register_activity

import com.systemtechnology.devregister.define_rules.ActivityMethods
import com.systemtechnology.devregister.define_rules.RulesBasePresenter
import com.systemtechnology.devregister.entity.Address
import com.systemtechnology.devregister.entity.Developer
import com.systemtechnology.devregister.validate_helper.ClientValidate
import io.reactivex.Observable

class UpdateOrRegisterPresenter( activityMethods : UpdateOrRegisterMethods ) : RulesBasePresenter( activityMethods ) {

    private val uorm = activityMethods
    val developer = Developer()


    override fun onCreate() {
        super.onCreate()

        uorm.notifyIsInsertingNewDeveloper()
    }

    fun whenClickedButtonConfirm(currentName: String, currentCPF: String) {
        developer.name = currentName
        developer.CPF  = currentCPF

        Observable
            .just( developer )
            .filter {
                val idMessage = ClientValidate.isValid( it )

                //if some thing wrong in form on layout
                if ( idMessage != 0 ) {
                    uorm.whenFormWrong( idMessage )
                    false
                } else {
                    true
                }

            }.doOnNext {
                developer.save()

                uorm.whenDeveloperWasSalved( it!! )

            }.subscribe()
    }

    fun whenReceiveAddress( address : Address) {
        developer.address = address
    }
}

interface UpdateOrRegisterMethods : ActivityMethods {
    fun whenFormWrong(idMessage: Int)
    fun whenDeveloperWasSalved(developer: Developer)
    fun notifyIsInsertingNewDeveloper()
}
