package com.systemtechnology.devregister.activities.register_activity

import com.systemtechnology.devregister.define_rules.AnyPresenter
import com.systemtechnology.devregister.define_rules.RulesBaseActivity

class ActivityRegisterActivity : ActivityRegisterActivityView() {


}

abstract class ActivityRegisterActivityView : RulesBaseActivity() {
    override fun getLayoutResActivity(): Int {
        return 0
    }

    override fun getReferences() {

    }

    override fun getInstancePresenter(): AnyPresenter {
        return ActivityRegisterPresenter( this )
    }

    override fun setSettingsIfExists() {

    }
}