package com.systemtechnology.devregister.activities.main_activity.adapter

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.support.v4.app.ActivityOptionsCompat
import android.view.View
import android.widget.TextView
import com.mikhaellopez.circularimageview.CircularImageView
import com.systemtechnology.devregister.R
import com.systemtechnology.devregister.activities.activity_developer_activity.ActivityDeveloperActivity
import com.systemtechnology.devregister.activities.activity_developer_details_dev.ActivityDevDetailsActivity
import com.systemtechnology.devregister.activities.update_or_register_activity.RegisterDeveloperActivity
import com.systemtechnology.devregister.configs.ConfigDirectory
import com.systemtechnology.devregister.define_rules.adapter.RulesHolderAdapterPhoto
import com.systemtechnology.devregister.entity.DeveloperEntity
import com.systemtechnology.devregister.utils.UtilsConvertJson
import com.systemtechnology.devregister.utils.UtilsFormat

import com.systemtechnology.devregister.helper_transition.TransitionHelper

class DevelopersHolder(view: View) : RulesHolderAdapterPhoto(view), View.OnClickListener {

    private lateinit var circularImageView  : CircularImageView
    private lateinit var textViewTitle      : TextView
    private lateinit var textViewSubTitle   : TextView

    private lateinit var developerEntity  : DeveloperEntity

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

        val it = Intent( itemView.context , ActivityDevDetailsActivity::class.java )
        it.putExtra( RegisterDeveloperActivity.EXTRA_DEVELOPER , UtilsConvertJson.toJson( developerEntity ) )

        if( TransitionHelper.isPossibleUseTransition() ) {
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                getContext() as Activity ,
                android.support.v4.util.Pair.create( itemView.findViewById(R.id.container_transition) , "container_transition" )
            )

            getContext().startActivity( it ,options.toBundle() )
        } else {
            getContext().startActivity( it )
        }

    }

    override fun bindViewHolder(obj: Any?) {
        developerEntity = obj as DeveloperEntity

        loadPhotoFromStorage(
            circularImageView ,
            ConfigDirectory.DIRECTORY_DEVELOPERS ,
            developerEntity.CPF
        )

        textViewTitle.text = developerEntity.name
        textViewSubTitle.text = UtilsFormat
                                    .formatAddressToPutOnLayout( developerEntity.addressEntity )
    }

}
