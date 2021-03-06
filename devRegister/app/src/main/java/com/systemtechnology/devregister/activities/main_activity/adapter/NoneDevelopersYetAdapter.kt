package com.systemtechnology.devregister.activities.main_activity.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.systemtechnology.devregister.R
import com.systemtechnology.devregister.define_rules.adapter.AdapterDependency
import com.systemtechnology.devregister.define_rules.adapter.RulesAdapterRecycler

class NoneDevelopersYetAdapter(ad: AdapterDependency ) : RulesAdapterRecycler<Any>( null , ad) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): RecyclerView.ViewHolder {
        return NoneDevelopersYetHolder( layoutInflater.inflate( R.layout.holder_none_developers_yet , viewGroup , false ) )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) { }

    override fun getItemCount(): Int {
        return 1
    }
}
