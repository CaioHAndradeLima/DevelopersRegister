package com.systemtechnology.devregister.activities.update_or_register_activity

import android.content.Context
import android.graphics.Bitmap
import com.systemtechnology.devregister.R
import com.systemtechnology.devregister.configs.ConfigDirectory
import com.systemtechnology.devregister.define_rules.ActivityMethods
import com.systemtechnology.devregister.define_rules.RulesBasePresenter
import com.systemtechnology.devregister.entity.AddressEntity
import com.systemtechnology.devregister.entity.DeveloperEntity
import com.systemtechnology.devregister.validate_helper.DeveloperValidate
import io.reactivex.Observable

class RegisterDeveloperPresenter(activityDeveloperMethods : RegisterDeveloperMethods ) : RulesBasePresenter( activityDeveloperMethods ) {

    private val uorm = activityDeveloperMethods
    val developer = DeveloperEntity()


    override fun onCreate() {
        super.onCreate()

        uorm.notifyIsInsertingNewDeveloper()
    }

    fun whenClickedButtonConfirm(currentName     : String,
                                 currentCPF      : String,
                                 currentEmail    : String,
                                 currentPassword : String) {
        developer.name  = currentName
        developer.CPF   = currentCPF
        developer.email = currentEmail
        developer.password = currentPassword

        Observable
            .just( developer )
            .filter {
                val idMessage = DeveloperValidate.isValid( it )

                //if some thing wrong in form on layout
                if ( idMessage != 0 ) {
                    uorm.whenFormWrong( idMessage )
                    false
                } else {
                    true
                }

            }.filter{

                if( !uorm.userAlreadyChoseThePhoto() ) {
                    uorm.whenFormWrong( R.string.form_client_error_photo )
                    false
                } else
                true
            }.doOnNext {
                it.save()

                caiohenrique
                    .auxphoto
                    .AuxiliarPhoto( getContext() )
                    .saveToInternalStorage(
                        ConfigDirectory.DIRECTORY_DEVELOPERS ,
                        uorm.getBitmap() ,
                        developer.CPF , // name of image
                        100 //quality percent
                    )

                uorm.whenDeveloperWasSalved( it!! )

            }.subscribe()
    }

    fun whenReceiveAddress(addressEntity : AddressEntity) {
        developer.addressEntity = addressEntity
    }

    private fun getContext() = uorm as Context

}

interface RegisterDeveloperMethods : ActivityMethods {

    fun whenFormWrong(idMessage: Int)
    fun whenDeveloperWasSalved(developerEntity: DeveloperEntity)
    fun notifyIsInsertingNewDeveloper()

    fun getBitmap() : Bitmap?
    fun userAlreadyChoseThePhoto() : Boolean
}
