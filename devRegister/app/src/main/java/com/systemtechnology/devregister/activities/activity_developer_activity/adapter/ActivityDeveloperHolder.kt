package com.systemtechnology.devregister.activities.activity_developer_activity.adapter

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

import com.systemtechnology.devregister.R
import com.systemtechnology.devregister.activities.activity_developer_details_dev.FactoryBottomDialogEntity

import com.systemtechnology.devregister.bottom_dialogs.BaseBottomDialog
import com.systemtechnology.devregister.bottom_dialogs.OptionsRecyclerBottomDialog

import com.systemtechnology.devregister.define_rules.adapter.RulesHolderAdapter
import com.systemtechnology.devregister.entity.ActivityDevEntity
import com.systemtechnology.devregister.messages_layout_entity.ActivityDevMessages
import com.systemtechnology.devregister.utils_textview.TextJustification

class ActivityDeveloperHolder(view: View) : RulesHolderAdapter( view ), View.OnClickListener {


    private lateinit var imageView          : ImageView
    private lateinit var textViewTitle      : TextView
    private lateinit var textViewSubTitle   : TextView
    private lateinit var textViewRequester  : TextView
    private lateinit var textViewDescription: TextView

    private lateinit var activityDev : ActivityDevEntity

    override fun getReferences() {
        imageView           = findViewById(R.id.imageview)
        textViewTitle       = findViewById(R.id.textview_title)
        textViewSubTitle    = findViewById(R.id.textview_subtitle)
        textViewRequester   = findViewById(R.id.textview_3)
        textViewDescription = findViewById(R.id.textview_4)
    }

    override fun onClick(v: View) {
        BaseBottomDialog
                .newInstance( OptionsRecyclerBottomDialog::class.java , Bundle() )
                .setListOptions( FactoryBottomDialogEntity
                                        .createOptions( activityDev )  {
                                            //when user click bottom Dialog
                                            Toast.makeText( getContext() ,
                                                    "the new status is ${it.name}" , Toast.LENGTH_SHORT).show()

                                        })
                .show( getFragmentManager() , "TAG_FRAGMENT_BOTTOM" )

    }

    override fun setSettingsWhenExists() {
        itemView.setOnClickListener( this )
    }

    override fun bindViewHolder(obj: Any?) {
        activityDev = obj as ActivityDevEntity

        textViewTitle.text = getString( ActivityDevMessages
                                            .getMessageStatus( activityDev ) )

        textViewSubTitle.text = ActivityDevMessages
                                             .getMessageDeliveryDate( activityDev  , getResources() )

        textViewRequester.text = ActivityDevMessages
                                              .getMessageRequester( activityDev  , getResources() )

        textViewDescription.text = activityDev.description

        TextJustification.justify( textViewDescription )

        imageView.setImageResource( ActivityDevMessages.getImageResource( activityDev ) )

    }


}
