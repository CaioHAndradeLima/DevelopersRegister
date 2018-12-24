package com.systemtechnology.devregister.activities.activity_developer_details_dev

import android.os.Build
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.systemtechnology.devregister.R
import com.systemtechnology.devregister.activities.activity_developer_activity.adapter.ActivityDeveloperAdapter
import com.systemtechnology.devregister.define_rules.AnyPresenter
import com.systemtechnology.devregister.define_rules.RulesBaseActivity
import com.systemtechnology.devregister.entity.ActivityDevEntity
import android.support.design.widget.AppBarLayout
import android.view.Menu
import android.view.MenuItem
import com.systemtechnology.devregister.activities.update_or_register_activity.RegisterDeveloperActivity
import com.systemtechnology.devregister.configs.ConfigDirectory
import com.systemtechnology.devregister.entity.DeveloperEntity
import com.systemtechnology.devregister.utils.UtilsLoaderPhoto
import kotlinx.android.synthetic.main.activity_dev_details.*
import kotlinx.android.synthetic.main.holder_developers.*


class ActivityDevDetailsActivity : RulesBaseActivity() {

    private lateinit var recyclerView : RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


    }

    override fun getLayoutResActivity(): Int {
        return R.layout.activity_dev_details
    }

    override fun getReferences() {
        recyclerView = recyclerview
    }

    override fun getInstancePresenter(): AnyPresenter {
        return ActivityDevDetailsPresenter( this )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate( R.menu.menu_exit , menu )
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if( item.itemId == R.id.ic_exit ) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun setSettingsIfExists() {
        setSupportActionBar( toolbar )

        UtilsLoaderPhoto.loadPhotoFromStorage( circularimageview ,
                                                ConfigDirectory.DIRECTORY_DEVELOPERS ,
                                                fromJson( intent.getStringExtra( RegisterDeveloperActivity.EXTRA_DEVELOPER ) , DeveloperEntity::class.java ).CPF
                                                )

        recyclerView.layoutManager = LinearLayoutManager( this )

        val a = mutableListOf<ActivityDevEntity>()

        for (i in 1 until 10) {
            val ade = ActivityDevEntity("Descricao desd sfiosfko fjiofsjsfj sjfijsf ",
                "desenvolvedor x" ,
                "desenvolvedor y",
                "date To Delivery")
            a.add( ade )
        }

        recyclerView.adapter = ActivityDeveloperAdapter( a  , this )



        app_bar.addOnOffsetChangedListener( listenerAnimation )

    }


    private val listenerAnimation = AppBarLayout.OnOffsetChangedListener { appBarLayout,
                                                     verticalOffset ->
        val newPercentage = Math.abs(verticalOffset).toFloat() / appBarLayout.totalScrollRange

        changeOpacity(newPercentage)
    }

    private fun changeOpacity(newPercentage: Float) {
     //   val newOpacidade = (newPercentage.toDouble() * 100.0 * 2.55).toInt()

        if( toolbar.alpha > newPercentage ) {
            if( newPercentage < 0.1F ) {
                toolbar.alpha = 0F
            }
            return
        }

        if (newPercentage >= 0.1f) {
            toolbar.alpha = newPercentage
        }
    }

}
