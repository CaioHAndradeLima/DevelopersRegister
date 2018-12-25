package com.systemtechnology.devregister.activities.activity_developer_activity.adapter

import android.view.View
import com.systemtechnology.devregister.activities.activity_developer_details_dev.ActivityDevDetailsActivity
import com.systemtechnology.devregister.define_rules.adapter.RulesHolderAdapter

class NoneActivityDevYetHolder(view: View) : RulesHolderAdapter(view) {

    override fun getReferences() {}

    override fun setSettingsWhenExists() {
        itemView.setOnClickListener {

            if( doubleClick.isSingleClick() )
                ( getContext() as ActivityDevDetailsActivity )
                    .openRegisterActivity()
        }
    }

    override fun bindViewHolder(obj: Any?) { }
}
