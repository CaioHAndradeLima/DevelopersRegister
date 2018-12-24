package com.systemtechnology.devregister.activities.main_activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.transition.TransitionInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog
import com.systemtechnology.devregister.R
import com.systemtechnology.devregister.activities.main_activity.adapter.DevelopersAdapter
import com.systemtechnology.devregister.activities.main_activity.adapter.NoneDevelopersYetAdapter
import com.systemtechnology.devregister.activities.update_or_register_activity.RegisterDeveloperActivity
import com.systemtechnology.devregister.define_rules.AnyPresenter
import com.systemtechnology.devregister.define_rules.RulesBaseActivityBroadcasts
import com.systemtechnology.devregister.entity.DeveloperEntity
import com.systemtechnology.devregister.helper_transition.TransitionHelper
import com.systemtechnology.devregister.utils_date.UtilsDate
import java.lang.IllegalStateException

class MainActivity : MainActivityView() ,
                     MainMethods {


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

            val jsonClient = intent.getStringExtra( RegisterDeveloperActivity.EXTRA_DEVELOPER )

            (presenter as MainPresenter)
                .whenClientModified(
                    fromJson( jsonClient , DeveloperEntity::class.java ) ,
                    true
                                    )
        } else {
            throw IllegalStateException("not implemented action ${intent.action}")
        }

    }

}

abstract class MainActivityView : RulesBaseActivityBroadcasts() {

    private lateinit var recyclerView   : RecyclerView

    private lateinit var developersAdapter         : DevelopersAdapter
    private lateinit var noneDevelopersYetAdapter  : NoneDevelopersYetAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        TransitionHelper.enableTransition(this)
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()

        val DATA_MAX_AFTER_CURRENT_DATA = 15
        val MINUTES_BETWEEN_HOURS = 15

        SingleDateAndTimePickerDialog.Builder(this)
            .bottomSheet()
            .curved()
            .title("Data de entrega")
            .titleTextColor(getColorCompat(R.color.blue_strong))
            .backgroundColor(getColorCompat( android.R.color.white))
            .mainColor(getColorCompat(R.color.blue_strong))
            .mustBeOnFuture()
            //  .displayListener({ Toast.makeText( this , "${it.date}" , Toast.LENGTH_SHORT).show() })
            .listener {
                //when user selected the date
                Toast.makeText(this, "${it.toString()}", Toast.LENGTH_SHORT).show()
            }
            .minDateRange( UtilsDate.getToday() )
            .maxDateRange( UtilsDate.getDateAfterDaysPassedByParam( DATA_MAX_AFTER_CURRENT_DATA  ) )
    //        .displayDays(true)
            .displayMonth(true)
            .displayYears(false )
            .displayHours(true)
            .displayMinutes(true)
            .displayAmPm( false )
            //.displayMonthNumbers( false )
            .minutesStep(MINUTES_BETWEEN_HOURS)
            .display()
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


    override fun getActions(): Array<String>? {
        return arrayOf( RegisterDeveloperActivity.ACTION_DEVELOPER_REGISTER )
    }


}