package com.systemtechnology.devregister.activities.activity_developer_activity.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.systemtechnology.devregister.R
import com.systemtechnology.devregister.define_rules.adapter.AdapterDependency
import com.systemtechnology.devregister.define_rules.adapter.RulesAdapterRecycler
import com.systemtechnology.devregister.entity.ActivityDevEntity

class NoneActivityDevYetAdapter(ad: AdapterDependency)
                                            : RulesAdapterRecycler<ActivityDevEntity>(null , ad) {

    override fun onCreateViewHolder( parent : ViewGroup, i: Int) : RecyclerView.ViewHolder {
        return NoneActivityDevYetHolder(
            layoutInflater.inflate(
                R.layout.holder_dev_none_activity_yet ,
                parent ,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) { }

    override fun getItemCount() : Int { return 1 }

}
