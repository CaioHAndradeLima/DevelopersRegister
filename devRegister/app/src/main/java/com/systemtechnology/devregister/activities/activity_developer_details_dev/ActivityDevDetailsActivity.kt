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
import android.support.v4.widget.SwipeRefreshLayout

import com.systemtechnology.design.components.AppBarHeaderUser
import com.systemtechnology.devregister.activities.activity_developer_activity.adapter.NoneActivityDevYetAdapter
import com.systemtechnology.devregister.activities.activity_developer_details_dev.bottom_sheet_dialog.ActvityDevOptionsBottomDialog
import com.systemtechnology.devregister.activities.activity_developer_details_dev.bottom_sheet_dialog.FactoryBottomDialogEntity
import com.systemtechnology.devregister.activities.register_activity.ActivityRegisterActivity
import com.systemtechnology.devregister.activities.register_developer.RegisterDeveloperActivity
import com.systemtechnology.devregister.bottom_dialogs.OptionsRecyclerBottomDialog
import com.systemtechnology.devregister.define_rules.RulesBaseActivityBroadcasts
import com.systemtechnology.devregister.entity.DeveloperEntity
import com.systemtechnology.devregister.helper_transition.TransitionHelper
import com.systemtechnology.devregister.class_methods_infix.view_methods_infix.makeAnimationByIndex
import com.systemtechnology.devregister.viewmodel.DeveloperViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_dev_details.*
import java.lang.IllegalStateException
import java.util.concurrent.TimeUnit


class ActivityDevDetailsActivity : RulesBaseActivityBroadcasts() {

    private lateinit var recyclerView : RecyclerView
    private lateinit var appBar       : AppBarLayout
    private lateinit var swipeRefresh : SwipeRefreshLayout

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
            .setNavigationOnClickListener {
                if( doubleClick!!.isSingleClick() )
                    openRegisterActivity()
            }
    }

    override fun getReferences() {
        recyclerView = recyclerview
        appBar       = findViewById( R.id.app_bar)
        swipeRefresh = swipe_refresh_layout
    }

    override fun getInstancePresenter(): AnyPresenter {
        return ActivityDevDetailsPresenter( this )
    }

    override fun setSettingsIfExists() {

        recyclerView.layoutManager = LinearLayoutManager( this )

        if( getDeveloper().listActivityDev.isNotEmpty() ) {
            recyclerView.adapter = ActivityDeveloperAdapter( getDeveloper().listActivityDev  , this )
        } else {
            recyclerView.adapter = NoneActivityDevYetAdapter( this )
        }

        swipeRefresh.setColorSchemeColors(
            getColorCompat( R.color.blue_strong ),
            getColorCompat( android.R.color.holo_red_dark ),
            getColorCompat( R.color.blue_strong ) )

        swipeRefresh.setOnRefreshListener {
            Observable
                .timer(2500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { swipeRefresh?.isRefreshing = false }
                .subscribe()
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

        TransitionHelper.startActivity( this , it ) {
            //invoked when will do the transition animation
            ActivityOptionsCompat.makeSceneTransitionAnimation(
                this ,
                android.support.v4.util.Pair.create( findViewById(R.id.container_transition) , "container_transition" )
            )
        }
    }

    fun getDeveloper() : DeveloperEntity {
        return getDeveloperViewModel().developer
    }

    fun getDeveloperViewModel() : DeveloperViewModel {
        return getViewModel( DeveloperViewModel::class.java )
    }

    override fun getActions(): Array<String>? {
        return arrayOf(
                    ActivityRegisterActivity.ACTION_ACTIVITY_DEV,
                    OptionsRecyclerBottomDialog.ACTION_OPTION_CLICKED )
    }

    override fun onReceiv(intent: Intent) {
        when {
            intent.action == ActivityRegisterActivity.ACTION_ACTIVITY_DEV -> notifyActionActivityRegister( intent )
            intent.action == OptionsRecyclerBottomDialog.ACTION_OPTION_CLICKED -> notifyActionSheetDialogOptions( intent )
            else -> throw IllegalStateException("not implemented ${intent.action}")
        }
    }

    private fun notifyActionSheetDialogOptions(intent: Intent) {
        //getting the new status of activity clicked on holder after selected the option on bottom dialog

        val newActivityStatus =
            FactoryBottomDialogEntity.getStatusByOption(
                fromJson( intent.getStringExtra( OptionsRecyclerBottomDialog.EXTRA_BOTTOM_DIALOG_ENTITY  ) )
            )

        val lastClicked = fromJson<ActivityDevEntity>(
                                            intent.getStringExtra( ActvityDevOptionsBottomDialog.ACTIVITY_DEV_ENTITY )
                                          )

        for ((index,it) in getDeveloper().listActivityDev.withIndex()) {
            if( lastClicked.id == it.id ) {
                it.status = newActivityStatus

                if( newActivityStatus == ActivityDevEntity.ActivityStatus.DELETED ) {
                    getDeveloper().listActivityDev.removeAt( index )
                    recyclerView.adapter!!.notifyItemRemoved( index )
                    it.delete()
                } else {
                    recyclerView.adapter!!.notifyItemChanged( index )
                    it.save()

                    recyclerView.
                        makeAnimationByIndex( index )

                }

                updateSubtitle()
                break
            }
        }
    }

    private fun updateSubtitle() {
        AppBarHeaderUser
            .getSubtitle( container )
            .text = getDeveloperViewModel().getSubtitle()

    }

    private fun notifyActionActivityRegister(intent : Intent) {
        val acDev = fromJson<ActivityDevEntity>(
                                        intent.getStringExtra( ActivityRegisterActivity.EXTRA_ACTIVITY_REGISTER_ENTITY )
                                    )

        val isInserting = intent.getBooleanExtra(
            ActivityRegisterActivity.EXTRA_ACTIVITY_REGISTER_ENTITY_INSERTING ,
            false
        )

        if( isInserting ) {

            getDeveloper().addActivityDev( acDev )

            if( recyclerView.adapter is ActivityDeveloperAdapter ) {
                recyclerView.adapter!!.notifyItemInserted( 0 )
                recyclerView.layoutManager!!.scrollToPosition( 0 )

                //recyclerView.makeAnimationByIndex( 0 )

            } else {
                recyclerView.adapter = ActivityDeveloperAdapter(
                    getDeveloper().listActivityDev ,
                    this
                )
            }

        } else {
            //is updating
            val index = ActivityDevDetailsPresenter.updateActivityDev(
                                getDeveloper() ,
                                acDev
                            )
            recyclerView.adapter!!.notifyItemChanged( index )

            //recyclerView.makeAnimationByIndex( index )
        }

        updateSubtitle()
    }

}
