package com.systemtechnology.devregister.animation_view

import android.view.View
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo

object AnimationViewFactory {


    fun create( view        : View ,
                animation   : Techniques = Techniques.Swing,
                duration    : Long = 700 ) {

        YoYo.with( animation )
            .duration( duration )
            .repeat(0)
            .playOn( view )

    }
}