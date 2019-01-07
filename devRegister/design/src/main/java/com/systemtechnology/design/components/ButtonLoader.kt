package com.systemtechnology.design.components

import android.app.Activity
import android.content.Context
import android.graphics.Typeface
import android.support.constraint.ConstraintLayout
import android.support.v4.content.res.ResourcesCompat
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import com.getkeepsafe.taptargetview.TapTarget
import com.getkeepsafe.taptargetview.TapTargetView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class ButtonLoader : ConstraintLayout {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    val button = Button( context )
    val view   = View( context )

    init {

        addView( setConfigButton() )
        addView( setConfigViewContainerAnimation() )
    }


    val target : TapTarget = TapTarget.forView(
        view,
        "Enviando informações",
        "Realizando login..."
    )
        // All options below are optional
        //.outerCircleColor(android.R.color.holo_red_dark)      // Specify a color for the outer circle
        .outerCircleAlpha(0.9f)            // Specify the alpha amount for the outer circle
        .targetCircleColor(android.R.color.white)   // Specify a color for the target circle
        .titleTextSize(25)                  // Specify the size (in sp) of the title text
        .titleTextColor(android.R.color.white)      // Specify the color of the title text
        .descriptionTextSize(20)            // Specify the size (in sp) of the description text
        .descriptionTextColor(android.R.color.white)  // Specify the color of the description text
        .textColor(android.R.color.white)            // Specify a color for both the title and description text
        .textTypeface(Typeface.SANS_SERIF)  // Specify a typeface for the text
        //.dimColor(android.R.color.black)            // If set, will dim behind the view with 30% opacity of the given color
        .drawShadow(true)                   // Whether to draw a drop shadow or not
        .cancelable(true)                  // Whether tapping outside the outer circle dismisses the view
        //.tintTarget(true)                   // Whether to tint the target view's color
        .transparentTarget(false)           // Specify whether the target is transparent (displays the content underneath)
        //.icon(Drawable)                     // Specify a custom drawable to draw as the target
        .targetRadius(60)

    private fun setConfigViewContainerAnimation() : View {
        val lpView = ConstraintLayout.LayoutParams(
                                120 ,
                                       ConstraintLayout.LayoutParams.WRAP_CONTENT
                                   )

        lpView.bottomToBottom = id

        view.layoutParams = lpView

        return view
    }

    private fun setConfigButton() : Button {
        val lp = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT ,
            ConstraintLayout.LayoutParams.MATCH_PARENT
        )

        lp.rightToRight = id
        lp.leftToLeft = id
        lp.bottomToBottom = id
        lp.topToTop = id

        button.layoutParams = lp

        button.setOnClickListener {


           val a = TapTargetView.showFor(
                                    context as Activity, // `this` is an Activity
                                    target
                                )


                    target
                        .outerCircleColor(android.R.color.holo_red_light)      // Specify a color for the outer circle
                        .targetCircleColor( android.R.color.white )
                        .titleTextColor( android.R.color.black )      // Specify the color of the title text
                        .descriptionTextColor( android.R.color.black )
                        .icon( ResourcesCompat.getDrawable( resources , android.R.drawable.ic_dialog_alert, null) )
                        .drawShadow( true )

                    a.dismiss( false )

                    TapTargetView.showFor( context as Activity  , target )

        }
        return button

    }



}
