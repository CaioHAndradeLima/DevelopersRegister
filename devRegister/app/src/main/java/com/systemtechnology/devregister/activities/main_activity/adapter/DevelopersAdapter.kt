package com.systemtechnology.devregister.activities.main_activity.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.systemtechnology.devregister.R
import com.systemtechnology.devregister.define_rules.adapter.AdapterDependency
import com.systemtechnology.devregister.define_rules.adapter.RulesAdapterRecycler
import com.systemtechnology.devregister.entity.DeveloperEntity

class DevelopersAdapter(list: List<DeveloperEntity>, ad: AdapterDependency) : RulesAdapterRecycler<DeveloperEntity>(list, ad) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): RecyclerView.ViewHolder {
        return DevelopersHolder( layoutInflater.inflate( R.layout.holder_developers , viewGroup , false ) )
    }
}
