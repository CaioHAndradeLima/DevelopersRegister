package com.systemtechnology.devregister.activities.main_activity.adapter

import android.app.Activity
import android.content.Intent
import android.support.v4.app.ActivityOptionsCompat
import android.view.View
import android.widget.TextView
import com.mikhaellopez.circularimageview.CircularImageView
import com.systemtechnology.devregister.R
import com.systemtechnology.devregister.activities.activity_developer_details_dev.ActivityDevDetailsActivity
import com.systemtechnology.devregister.activities.register_developer.RegisterDeveloperActivity

import com.systemtechnology.devregister.define_rules.RulesBaseActivity
import com.systemtechnology.devregister.define_rules.adapter.RulesHolderAdapterPhoto

import com.systemtechnology.devregister.entity.DeveloperEntity
import com.systemtechnology.devregister.utils.UtilsConvertJson

import com.systemtechnology.devregister.helper_transition.TransitionHelper
import com.systemtechnology.devregister.viewmodel.DeveloperViewModel

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

        TransitionHelper.startActivity( getActivity() , it ) {
            ActivityOptionsCompat.makeSceneTransitionAnimation(
                getContext() as Activity ,
                android.support.v4.util.Pair.create( itemView.findViewById(R.id.container_transition) , "container_transition" )
            )
        }

    }

    @Synchronized
    override fun bindViewHolder(obj: Any?) {
        developerEntity = obj as DeveloperEntity

        val developerViewModel = getViewModel()

        developerViewModel.loadPhoto( circularImageView )

        textViewTitle.text = developerViewModel.getTitle()

        textViewSubTitle.text = developerViewModel.getSubtitle()
    }

    fun updateActivitiesTextView() {
        textViewSubTitle.text = getViewModel().getSubtitle()
    }

    fun getViewModel() : DeveloperViewModel {
        return ( getContext() as RulesBaseActivity )
                    .getViewModel( DeveloperViewModel::class.java  )
                    .setDeveloper( developerEntity )
    }

}
