package com.systemtechnology.devregister.activities.get_address_by_cep.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.systemtechnology.devregister.R
import com.systemtechnology.devregister.define_rules.adapter.AdapterDependency
import com.systemtechnology.devregister.define_rules.adapter.HolderMethods
import com.systemtechnology.devregister.define_rules.adapter.RulesAdapterRecycler

class GetAddressByCepAdapter(ad: AdapterDependency) : RulesAdapterRecycler<Any>( null , ad ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CepDetailsHolder( layoutInflater.inflate( R.layout.holder_cep_details , parent, false ) )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as HolderMethods).bindViewHolder( null )
    }

    override fun getItemCount(): Int {
        return 1
    }
}
