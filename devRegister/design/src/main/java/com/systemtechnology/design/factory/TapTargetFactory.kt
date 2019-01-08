package com.systemtechnology.design.factory

import android.support.v4.content.res.ResourcesCompat
import android.view.View
import com.getkeepsafe.taptargetview.TapTarget
import com.systemtechnology.design.R

object TapTargetFactory {

    fun createSampleTarget(view : View,
                           titleMsg : String,
                           description : String? ): TapTarget {

        return setBasicConfigTargetView(
                    TapTarget.forView(
                        view,
                        titleMsg,
                        description
                    )
                )


    }

    private fun setBasicConfigTargetView(target : TapTarget ) : TapTarget {
        target
            // All options below are optional
            //.outerCircleColor(android.R.color.holo_red_dark)      // Specify a color for the outer circle
            .outerCircleAlpha(0.9f)            // Specify the alpha amount for the outer circle
            .descriptionTextSize(20)            // Specify the size (in sp) of the description text
            .targetCircleColor(android.R.color.white)   // Specify a color for the target circle
            .titleTextSize(25)                  // Specify the size (in sp) of the title text
            .cancelable( false )                  // Whether tapping outside the outer circle dismisses the view
            //.dimColor(android.R.color.black)            // If set, will dim behind the view with 30% opacity of the given color
            .drawShadow(true)                   // Whether to draw a drop shadow or not
            //.tintTarget(true)                   // Whether to tint the target view's color
            .transparentTarget(false)           // Specify whether the target is transparent (displays the content underneath)
            //.icon(Drawable)                     // Specify a custom drawable to draw as the target
            .targetRadius(60)

        return target
    }

    fun createErrorTarget(view: View, titleMsg: String, description: String?): TapTarget {
        return setBasicConfigTargetView(
                    TapTarget.forView(
                        view,
                        titleMsg,
                        description
                    )
                )
                .outerCircleColor(android.R.color.holo_red_light)      // Specify a color for the outer circle
                .targetCircleColor( android.R.color.white )
                .titleTextColor( android.R.color.black )      // Specify the color of the title text
                .descriptionTextColor( android.R.color.black )
                .icon( ResourcesCompat.getDrawable( view.context.resources , android.R.drawable.ic_dialog_alert, null) )
                .drawShadow( true )

    }

    fun createErrorConnectionTarget(view : View , title: String, description: String): TapTarget {
        return setBasicConfigTargetView(
                    TapTarget.forView(
                        view,
                        title,
                        description
                    )
                )
                .outerCircleColor( R.color.yellow  )      // Specify a color for the outer circle
                .targetCircleColor( android.R.color.white )
                .titleTextColor( android.R.color.black )      // Specify the color of the title text
                .descriptionTextColor( android.R.color.black )
                .icon( ResourcesCompat.getDrawable( view.context.resources , android.R.drawable.ic_dialog_alert, null) )
                .drawShadow( true )
    }

}
