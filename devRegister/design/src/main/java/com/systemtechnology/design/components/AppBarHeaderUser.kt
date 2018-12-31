package com.systemtechnology.design.components

import android.app.Activity
import android.arch.lifecycle.ViewModel
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.NavigationView
import android.support.v7.widget.CardView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.systemtechnology.design.R

class AppBarHeaderUser private constructor(private val ac: Activity) {

    lateinit var toolbar : Toolbar

    internal fun build( container : CoordinatorLayout ,
                        abhum : AppBarHeaderUserMethods ) : AppBarHeaderUser {

         LayoutInflater
                    .from( ac )
                    .inflate(R.layout.app_bar_header_user ,
                             container ,
                        true)

        toolbar = container.findViewById( R.id.toolbar )
        inflateMenuToolbar()

        container.findViewById<AppBarLayout>(
            R.id.app_bar
        ).addOnOffsetChangedListener( listenerAnimation )

        abhum.loadPhoto( container.findViewById( R.id.circularimageview  ) )
        container.findViewById<TextView>( R.id.textview_title     ).text = abhum.getTitle()
        container.findViewById<TextView>( R.id.textview_subtitle  ).text = abhum.getSubtitle()

        return this
    }

    private fun inflateMenuToolbar() {
        toolbar.inflateMenu( R.menu.menu_exit )
        toolbar.setOnMenuItemClickListener {
            if( it.itemId == R.id.ic_exit ) {
                ac.onBackPressed()
            }
            false
        }
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

    fun setToolbarTitle( title : String ) : AppBarHeaderUser {
        toolbar.title = title

        return this
    }

    fun setNavigationIcon( navigationItem : Int ) : AppBarHeaderUser {
        toolbar.setNavigationIcon( navigationItem )

        return this
    }

    inline fun setNavigationOnClickListener( crossinline onClickListener : (view : View) -> Unit ) : AppBarHeaderUser {
        toolbar.setNavigationOnClickListener {
            onClickListener( it )
        }

        return this
    }

    companion object Build {

        @JvmStatic
        fun create( ac : Activity, container : CoordinatorLayout , methods : AppBarHeaderUserMethods ) : AppBarHeaderUser{
            return AppBarHeaderUser( ac ).build( container , methods )
        }

        @JvmStatic
        fun getSubtitle(container: CoordinatorLayout): TextView {
            return container
                .findViewById<AppBarLayout>( R.id.app_bar)
                .findViewById( R.id.textview_subtitle )
        }
    }

    interface AppBarHeaderUserMethods {
        fun getTitle() : String
        fun getSubtitle() : String
        fun loadPhoto( imageView : ImageView )
    }

}
