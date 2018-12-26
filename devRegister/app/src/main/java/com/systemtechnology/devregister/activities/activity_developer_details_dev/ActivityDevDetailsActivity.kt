package com.systemtechnology.devregister.activities.activity_developer_details_dev

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.systemtechnology.devregister.R
import com.systemtechnology.devregister.activities.activity_developer_activity.adapter.ActivityDeveloperAdapter
import com.systemtechnology.devregister.define_rules.AnyPresenter
import com.systemtechnology.devregister.entity.ActivityDevEntity
import android.support.design.widget.AppBarLayout
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.widget.Toolbar

import com.systemtechnology.design.components.AppBarHeaderUser
import com.systemtechnology.devregister.activities.activity_developer_activity.adapter.NoneActivityDevYetAdapter
import com.systemtechnology.devregister.activities.register_activity.ActivityRegisterActivity
import com.systemtechnology.devregister.activities.register_developer.RegisterDeveloperActivity
import com.systemtechnology.devregister.define_rules.RulesBaseActivityBroadcasts
import com.systemtechnology.devregister.entity.DeveloperEntity
import com.systemtechnology.devregister.helper_transition.TransitionHelper
import com.systemtechnology.devregister.viewmodel.DeveloperViewModel
import kotlinx.android.synthetic.main.activity_dev_details.*
import java.lang.IllegalStateException


class ActivityDevDetailsActivity : RulesBaseActivityBroadcasts() {

    private lateinit var recyclerView : RecyclerView
    private lateinit var appBar       : AppBarLayout
    private lateinit var toolbar      : Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        TransitionHelper.enableTransition( this )
        super.onCreate(savedInstanceState)
    }

    override fun getLayoutResActivity(): Int {
        return R.layout.activity_dev_details
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        inflateAppBarHeaderUser()
            .setToolbarTitle( getString(R.string.activities) )
            .setNavigationIcon( R.drawable.ic_activity_done )
    }

    override fun getReferences() {
        recyclerView = recyclerview
        appBar       = findViewById( R.id.app_bar)
        toolbar      = findViewById( R.id.toolbar )
    }

    private fun getSceneTransitionAnimation(): Bundle {
        return ActivityOptionsCompat.makeSceneTransitionAnimation(
            this ,
            android.support.v4.util.Pair.create( findViewById(R.id.container_transition) , "container_transition" )
        ).toBundle()!!
    }

    override fun getInstancePresenter(): AnyPresenter {
        return ActivityDevDetailsPresenter( this )
    }

    override fun setSettingsIfExists() {

        toolbar.setNavigationOnClickListener {
            if( doubleClick!!.isSingleClick() )
                openRegisterActivity()
        }

        recyclerView.layoutManager = LinearLayoutManager( this )

        if( getDeveloper().listActivityDev.isNotEmpty() ) {
            recyclerView.adapter = ActivityDeveloperAdapter( getDeveloper().listActivityDev  , this )
        } else {
            recyclerView.adapter = NoneActivityDevYetAdapter( this )
        }

    }

    fun openRegisterActivity( ade : ActivityDevEntity? = null) {
        val it = Intent( this , ActivityRegisterActivity::class.java )
        it.putExtra( RegisterDeveloperActivity.EXTRA_DEVELOPER  ,
            intent.getStringExtra( RegisterDeveloperActivity.EXTRA_DEVELOPER )
        )

        if( ade != null ) {
            it.putExtra( ActivityRegisterActivity.EXTRA_ACTIVITY_REGISTER_ENTITY , toJson( ade ) )
        }

        if( TransitionHelper.isPossibleUseTransition() ) {

            startActivity( it , getSceneTransitionAnimation())
        } else {
            startActivity( it )
        }
    }

    fun getDeveloper() : DeveloperEntity {
        return getDeveloperViewModel().developer
    }

    fun getDeveloperViewModel() : DeveloperViewModel {
        return getViewModel( DeveloperViewModel::class.java )
    }

    override fun getActions(): Array<String>? {
        return arrayOf( ActivityRegisterActivity.ACTION_ACTIVITY_DEV )
    }

    override fun onReceiv(intent: Intent) {
        if( intent.action == ActivityRegisterActivity.ACTION_ACTIVITY_DEV ) {

            val acDev = fromJson( intent.getStringExtra( ActivityRegisterActivity.EXTRA_ACTIVITY_REGISTER_ENTITY ) ,
                                                 ActivityDevEntity::class.java )

            getDeveloper().addActivityDev( acDev )

            AppBarHeaderUser
                .getSubtitle( container )
                .text = getDeveloperViewModel().getSubtitle()


            if( recyclerView.adapter is ActivityDeveloperAdapter ) {
                recyclerView.adapter!!.notifyItemInserted( 0 )
                recyclerView.layoutManager!!.scrollToPosition( 0 )

            } else {
                recyclerView.adapter = ActivityDeveloperAdapter(
                    getDeveloper().listActivityDev ,
                    this
                )
            }

        } else {
            throw IllegalStateException("not implemented ${intent.action}")
        }
    }

}
