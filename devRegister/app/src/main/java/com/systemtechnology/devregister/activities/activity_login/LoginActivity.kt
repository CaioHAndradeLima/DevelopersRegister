package com.systemtechnology.devregister.activities.activity_login


import com.systemtechnology.devregister.R
import com.systemtechnology.devregister.define_rules.AnyPresenter
import com.systemtechnology.devregister.define_rules.RulesBaseActivity

class LoginActivity : RulesBaseActivity() {

    override fun getLayoutResActivity(): Int {
        return R.layout.activity_login
    }

    override fun getReferences() {

    }

    override fun getInstancePresenter(): AnyPresenter {
        return LoginPresenter(this)
    }

    override fun setSettingsIfExists() {

    }
}
