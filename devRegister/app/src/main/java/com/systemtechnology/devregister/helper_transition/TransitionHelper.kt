package com.systemtechnology.devregister.helper_transition

import android.app.Activity
import android.os.Build
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


}
