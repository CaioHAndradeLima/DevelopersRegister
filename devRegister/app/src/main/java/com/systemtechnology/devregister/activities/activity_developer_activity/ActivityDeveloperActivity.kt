package com.systemtechnology.devregister.activities.activity_developer_activity

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.systemtechnology.devregister.R
import com.systemtechnology.devregister.activities.activity_developer_activity.adapter.ActivityDeveloperAdapter
import com.systemtechnology.devregister.define_rules.AnyPresenter
import com.systemtechnology.devregister.define_rules.RulesBaseActivity
import com.systemtechnology.devregister.entity.ActivityDevEntity
import kotlinx.android.synthetic.main.activity_dev_activity.*


class ActivityDeveloperActivity : ActivityDeveloperActivityView(), ActivityDeveloper {

    override fun notifyListArrived(list: MutableList<ActivityDevEntity>) {
        //changeAdapterNoneActivityDevYet( list )
    }

    override fun notifyNoneActivityDevYet() {
        changeAdapterNoneActivityDevYet()
    }

    override fun getInstancePresenter() : AnyPresenter {
        return ActivityDeveloperPresenter( this )
    }
}

abstract class ActivityDeveloperActivityView : RulesBaseActivity() {

    private lateinit var recyclerView : RecyclerView

    override fun getLayoutResActivity(): Int {
        return R.layout.activity_dev_activity
    }

    override fun getReferences() {
        recyclerView = recyclerview
        val ade = ActivityDevEntity("Descricao desd sfiosfko fjiofsjsfj sjfijsf ",
            "desenvolvedor x" ,
            "desenvolvedor y",
            "date To Delivery")

        recyclerView.adapter = ActivityDeveloperAdapter(  mutableListOf( ade ) , this )

    }

    override fun setSettingsIfExists() {
        recyclerView.layoutManager = LinearLayoutManager( this )
    }

    protected fun changeAdapterNoneActivityDevYet() {
 //       recyclerView.adapter = NoneActivityDevYetAdapter( this )
    }

}
