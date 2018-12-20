package com.systemtechnology.devregister.activities.main_activity.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.systemtechnology.devregister.R
import com.systemtechnology.devregister.define_rules.adapter.AdapterDependency
import com.systemtechnology.devregister.define_rules.adapter.RulesAdapterRecycler
import com.systemtechnology.devregister.entity.Developer

class ClientsAdapter(list: List<Developer>, ad: AdapterDependency) : RulesAdapterRecycler<Developer>(list, ad) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): RecyclerView.ViewHolder {
        return ClientsHolder( layoutInflater.inflate( R.layout.holder_clients , viewGroup , false ) )
    }
}
