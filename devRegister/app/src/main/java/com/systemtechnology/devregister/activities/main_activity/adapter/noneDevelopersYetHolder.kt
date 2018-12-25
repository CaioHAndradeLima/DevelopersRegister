package com.systemtechnology.devregister.activities.main_activity.adapter

import android.content.Intent
import android.view.View
import android.widget.TextView
import com.mikhaellopez.circularimageview.CircularImageView
import com.systemtechnology.devregister.R
import com.systemtechnology.devregister.activities.register_developer.RegisterDeveloperActivity
import com.systemtechnology.devregister.define_rules.adapter.RulesHolderAdapter
import java.lang.IllegalStateException

class NoneDevelopersYetHolder(view: View) : RulesHolderAdapter(view), View.OnClickListener {
    private lateinit var circularImageView  : CircularImageView
    private lateinit var textView           : TextView

    override fun getReferences() {
        circularImageView   = findViewById( R.id.circularimageview )
        textView            = findViewById( R.id.textview )

    }

    override fun setSettingsWhenExists() {
        itemView.setOnClickListener( this )
    }

    override fun onClick(v: View?) {
        if( doubleClick.isSingleClick() )
            itemView.context.startActivity( Intent( itemView.context , RegisterDeveloperActivity::class.java ) )
    }

    override fun bindViewHolder(obj: Any?) {
        throw IllegalStateException("don't need call bindViewHolder")
    }
}
