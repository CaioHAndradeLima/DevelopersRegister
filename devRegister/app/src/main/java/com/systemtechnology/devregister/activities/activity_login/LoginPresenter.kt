package com.systemtechnology.devregister.activities.activity_login

import com.systemtechnology.devregister.define_rules.ActivityMethods
import com.systemtechnology.devregister.define_rules.RulesBasePresenter
import com.systemtechnology.devregister.entity.DeveloperEntity
import com.systemtechnology.devregister.model.ModelDeveloper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

internal class LoginPresenter(private val lm: LoginMethods) : RulesBasePresenter(lm) {

    fun whenUserTryDoLogin(loginEntity: LoginEntity) {

        addDisposable(
            Observable
                .just( loginEntity )
                .observeOn( AndroidSchedulers.mainThread() )
                .subscribeOn( Schedulers.io() )
                .map {

                    //where should be done the where query in data base
                    //but this way is fastest to change the real server
                    for (dev in ModelDeveloper().getAllDevelopers()!!) {
                        if( dev.email == it.emailOrCPF ||
                            dev.CPF   == it.emailOrCPF ) {

                            return@map dev
                        }
                    }

                    throw LoginNotFound()

                }
                .subscribe({ lm.whenLogged( it ) }) {
                    lm.whenNotFound()
                }
        )

    }

}

class LoginNotFound : Exception()

interface LoginMethods : ActivityMethods {
    fun whenLogged( developerEntity : DeveloperEntity )
    fun whenNotFound()
}