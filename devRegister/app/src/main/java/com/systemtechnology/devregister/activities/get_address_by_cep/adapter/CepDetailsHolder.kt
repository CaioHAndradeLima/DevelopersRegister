package com.systemtechnology.devregister.activities.get_address_by_cep.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.systemtechnology.devregister.R
import com.systemtechnology.devregister.define_rules.adapter.RulesHolderAdapter

class CepDetailsHolder( view : View) : RulesHolderAdapter( view ) {

    lateinit var imageView: ImageView
    lateinit var textView: TextView

    override fun getReferences() {
        imageView = findViewById(R.id.imageview)
        textView  = findViewById(R.id.textview)
    }

    override fun bindViewHolder(obj: Any?) {
        imageView.setImageResource( R.drawable.red_places  )
        textView.text = "Digite seu CEP para encontrarmos seu endereço"
    }

}