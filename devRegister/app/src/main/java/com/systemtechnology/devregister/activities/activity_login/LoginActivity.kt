package com.systemtechnology.devregister.activities.activity_login

import android.widget.EditText
import com.systemtechnology.design.components.ButtonBuildFactory
import com.systemtechnology.design.components.ButtonLoader
import com.systemtechnology.devregister.R
import com.systemtechnology.devregister.define_rules.AnyPresenter
import com.systemtechnology.devregister.define_rules.RulesBaseActivity
import com.systemtechnology.devregister.entity.DeveloperEntity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : LoginActivityView(), LoginMethods {

    override fun whenNotFound() {
        openSnackBar("Não existe este usuário")
    }

    override fun whenLogged(developerEntity: DeveloperEntity) {
        openSnackBar("user found " + developerEntity.name )
    }


    override fun getInstancePresenter(): AnyPresenter {
        return LoginPresenter(this)
    }
}

abstract class LoginActivityView : RulesBaseActivity() {

    private lateinit var buttonLoader : ButtonLoader

    private lateinit var edtEmailOrCPF  : EditText
    private lateinit var edtPassword    : EditText

    override fun getLayoutResActivity(): Int {
        return R.layout.activity_login
    }

    override fun getReferences() {
        buttonLoader = button_loader
        edtEmailOrCPF = edittext_email
        edtPassword   = edittext_password
    }

    override fun setSettingsIfExists() {

        buttonLoader
            .setButtonBuild(
                ButtonBuildFactory.getDefaultFactory( this )
            ).setOnButtonClickListener {
                val loginEntity = getCurrentLoginEntity()

                val msg = LoginValidate.validate( loginEntity )

                if( msg == 0 ) {
                    buttonLoader.showTargetProgress(
                        "Fazendo login..." ,
                        ""
                    )
                    (presenter as LoginPresenter)
                        .whenUserTryDoLogin( loginEntity )

                } else {
                     openSnackBar( getString( msg ) )
                }

            }
    }

    private fun getCurrentLoginEntity(): LoginEntity {
        return LoginEntity(
            edtEmailOrCPF.text.toString() ,
            edtPassword.text.toString()
        )
    }
}
