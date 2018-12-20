package com.systemtechnology.devregister.activities.main_activity.adapter

import android.content.Intent
import android.view.View
import android.widget.TextView
import com.mikhaellopez.circularimageview.CircularImageView
import com.systemtechnology.devregister.R
import com.systemtechnology.devregister.activities.update_or_register_activity.UpdateOrRegisterActivity
import com.systemtechnology.devregister.define_rules.adapter.RulesHolderAdapter
import com.systemtechnology.devregister.entity.Developer
import com.systemtechnology.devregister.utils.UtilsConvertJson
import com.systemtechnology.devregister.utils.UtilsFormat

class ClientsHolder(view: View) : RulesHolderAdapter(view), View.OnClickListener {

    private lateinit var circularImageView  : CircularImageView
    private lateinit var textViewTitle      : TextView
    private lateinit var textViewSubTitle   : TextView

    private lateinit var developer             : Developer

    override fun getReferences() {
        circularImageView   = findViewById( R.id.circularimageview  )
        textViewTitle       = findViewById( R.id.textview_title     )
        textViewSubTitle    = findViewById( R.id.textview_subtitle  )
    }

    override fun setSettingsWhenExists() {
        itemView.setOnClickListener( this )
    }

    override fun onClick( itemView : View ) {
        if( doubleClick.isDoubleClick() ) return

        val it = Intent( itemView.context , UpdateOrRegisterActivity::class.java )
        it.putExtra( UpdateOrRegisterActivity.EXTRA_CLIENT , UtilsConvertJson.toJson( developer ) )
        itemView.context.startActivity( it )
    }

    override fun bindViewHolder(obj: Any?) {
        developer = obj as Developer

        textViewTitle.text = developer.name
        textViewSubTitle.text = UtilsFormat
                                    .formatAddressToPutOnLayout( developer.address )
    }
}
