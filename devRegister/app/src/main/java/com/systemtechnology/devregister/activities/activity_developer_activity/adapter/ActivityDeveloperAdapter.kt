package com.systemtechnology.devregister.activities.activity_developer_activity.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

import com.systemtechnology.devregister.R
import com.systemtechnology.devregister.define_rules.adapter.AdapterDependency
import com.systemtechnology.devregister.define_rules.adapter.RulesAdapterRecycler
import com.systemtechnology.devregister.entity.ActivityDevEntity

class ActivityDeveloperAdapter(list: List<ActivityDevEntity>,
                               ad: AdapterDependency) :
                                            RulesAdapterRecycler<ActivityDevEntity>(list, ad) {


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): RecyclerView.ViewHolder {
        return ActivityDeveloperHolder(
            layoutInflater.inflate(
                R.layout.holder_activity_developer,
                viewGroup,
                false
            )
        )
    }

}
