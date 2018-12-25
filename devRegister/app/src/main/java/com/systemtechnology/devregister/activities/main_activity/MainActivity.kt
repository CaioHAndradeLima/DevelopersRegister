package com.systemtechnology.devregister.activities.main_activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import com.systemtechnology.devregister.R
import com.systemtechnology.devregister.activities.main_activity.adapter.DevelopersAdapter
import com.systemtechnology.devregister.activities.main_activity.adapter.DevelopersHolder
import com.systemtechnology.devregister.activities.main_activity.adapter.NoneDevelopersYetAdapter
import com.systemtechnology.devregister.activities.register_activity.ActivityRegisterActivity
import com.systemtechnology.devregister.activities.register_developer.RegisterDeveloperActivity
import com.systemtechnology.devregister.define_rules.AnyPresenter
import com.systemtechnology.devregister.define_rules.RulesBaseActivityBroadcasts
import com.systemtechnology.devregister.entity.ActivityDevEntity
import com.systemtechnology.devregister.entity.DeveloperEntity
import com.systemtechnology.devregister.helper_transition.TransitionHelper
import java.lang.IllegalStateException

class MainActivity : MainActivityView() ,
                     MainMethods {

    private var indexThatNeedUpdateAfterAnimation = -1

    override fun whenListModified(list: MutableList<DeveloperEntity>) {
        changeAdapterRecyclerToDevelopersAdapter( list )
    }

    override fun whenNotExistsDevelopersYet() {
        changeAdapterRecyclerToNoneDevelopersYetAdapter()
    }

    override fun whenDeveloperFound(listDeveloperEntities: MutableList<DeveloperEntity>) {
        changeAdapterRecyclerToDevelopersAdapter( listDeveloperEntities )
    }

    override fun getInstancePresenter() : AnyPresenter {
        return MainPresenter( this )
    }

    override fun onCreateOptionsMenu(menu: Menu) : Boolean {
        menuInflater.inflate( R.menu.menu_more , menu )
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(doubleClick!!.isSingleClick())
            startActivity( Intent( this , RegisterDeveloperActivity::class.java ) )

        return false
    }

    override fun onReceiv(intent: Intent) {
        if( intent.action == RegisterDeveloperActivity.ACTION_DEVELOPER_REGISTER ) {

            val jsonDeveloper = intent.getStringExtra( RegisterDeveloperActivity.EXTRA_DEVELOPER )

            (presenter as MainPresenter)
                .whenDeveloperModified(
                    fromJson( jsonDeveloper , DeveloperEntity::class.java ) ,
                    true
                                    )
        } else if( intent.action == ActivityRegisterActivity.ACTION_ACTIVITY_DEV ) {

            val jsonActivityDevEntity = intent.getStringExtra( ActivityRegisterActivity.EXTRA_ACTIVITY_REGISTER_ENTITY )

            (presenter as MainPresenter)
                .whenActivityDevEntityModified(
                    fromJson( jsonActivityDevEntity , ActivityDevEntity::class.java ) ,
                    true
                )

        } else {
            throw IllegalStateException("not implemented action ${intent.action}")
        }
    }

    override fun whenNewActivityDevInserted(ade: ActivityDevEntity , indexPosition : Int ) {
        (recyclerView
            .findViewHolderForAdapterPosition( indexPosition )
            as DevelopersHolder?)?.updateActivitiesTextView()
 //       indexThatNeedUpdateAfterAnimation = indexPosition
    }
}

abstract class MainActivityView : RulesBaseActivityBroadcasts() {

    protected lateinit var recyclerView   : RecyclerView

    private lateinit var developersAdapter         : DevelopersAdapter
    private lateinit var noneDevelopersYetAdapter  : NoneDevelopersYetAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        TransitionHelper.enableTransition(this)
        super.onCreate(savedInstanceState)
    }

    override fun getLayoutResActivity() : Int {
        return R.layout.activity_main
    }

    override fun getReferences() {
        recyclerView = findViewById(R.id.recyclerview)
    }

    override fun setSettingsIfExists() {
        recyclerView.layoutManager  = LinearLayoutManager( this )
    }

    protected fun changeAdapterRecyclerToNoneDevelopersYetAdapter() {
        noneDevelopersYetAdapter = NoneDevelopersYetAdapter(this )
        recyclerView.adapter = noneDevelopersYetAdapter
    }

    protected fun changeAdapterRecyclerToDevelopersAdapter(listDeveloperEntities: MutableList<DeveloperEntity>) {
        developersAdapter = DevelopersAdapter( listDeveloperEntities , this )
        recyclerView.adapter = developersAdapter
    }

    protected fun getDevelopersAdapter() : DevelopersAdapter {
        return recyclerView.adapter as DevelopersAdapter
    }

    override fun getActions(): Array<String>? {
        return arrayOf( RegisterDeveloperActivity.ACTION_DEVELOPER_REGISTER ,
                        ActivityRegisterActivity.ACTION_ACTIVITY_DEV )
    }


}