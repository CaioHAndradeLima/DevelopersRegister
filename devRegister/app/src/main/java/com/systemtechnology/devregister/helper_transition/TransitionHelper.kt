package com.systemtechnology.devregister.helper_transition

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.support.v4.app.ActivityOptionsCompat
import android.transition.TransitionInflater

import com.systemtechnology.devregister.R

object TransitionHelper {

    @JvmStatic
    fun enableTransition( activity : Activity ) {
        if( isPossibleUseTransition() ) {

             activity
                .window
                .sharedElementExitTransition = TransitionInflater
                                                    .from( activity )
                                                    .inflateTransition( R.transition.transitions )
        }
    }

    @JvmStatic
    fun isPossibleUseTransition() : Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
    }

    /**
     * to create instance of ActivityOptionsCompat
     * use ActivityOptionsCompat.makeSceneTransitionAnimation() method
     */
    @JvmStatic
    inline fun startActivity(activity : Activity ,it: Intent ,crossinline getter : () -> ActivityOptionsCompat  ) {

        if( TransitionHelper.isPossibleUseTransition() ) {
            activity.startActivity( it , getter().toBundle() )
        } else {
            activity.startActivity( it )
        }

    }


}
