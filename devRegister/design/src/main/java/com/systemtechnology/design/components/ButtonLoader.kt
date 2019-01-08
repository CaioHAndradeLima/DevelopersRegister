package com.systemtechnology.design.components

import android.app.Activity
import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat

import android.util.AttributeSet
import android.view.View
import android.widget.Button
import com.getkeepsafe.taptargetview.TapTarget
import com.getkeepsafe.taptargetview.TapTargetView
import com.systemtechnology.design.R
import com.systemtechnology.design.factory.TapTargetFactory
import com.systemtechnology.design.utils.DoubleClick
import com.systemtechnology.design.utils.UtilsConnection


class ButtonLoader : ConstraintLayout {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    val button = Button( context )
    val view   = View( context )
    private lateinit var target : TapTarget
    var isRequiredValiateConnection = true


    val doubleClick = DoubleClick()

    init {

        addView( setConfigButton() )
        addView( setConfigViewContainerAnimation() )
    }


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

        return button
    }

    inline fun setOnButtonClickListener(crossinline onListenerClick : (view : View) -> Unit ) {
        button.setOnClickListener {

            if( doubleClick.isDoubleClick() ) return@setOnClickListener


           if( isRequiredValiateConnection &&
                    UtilsConnection.isNotConnected( context ) ) {

                showTargetErrorConnection(
                    context.getString(R.string.without_connection) ,
                    context.getString(R.string.without_connection_description)
                )
            } else {
               onListenerClick( it )
            }

        }

    }

    fun showTargetErrorConnection(title: String, description: String) {
        target = TapTargetFactory.createErrorConnectionTarget( view , title , description )
        showTargetView()
    }

    fun showTargetProgress(titleMsg : String , description : String?) {
        target = TapTargetFactory.createSampleTarget( view , titleMsg  , description  )
        showTargetView()
    }

    fun showTargetError( titleMsg : String , description : String? ) {
        target = TapTargetFactory.createErrorTarget( view , titleMsg  , description  )

        showTargetView()
    }

    fun showTargetView() {
        TapTargetView.showFor(
            context as Activity, // `this` is an Activity
            target
        )
    }

    fun setButtonBuild( bbf : ButtonBuildFactory ) : ButtonLoader{
        button.text = bbf.title
        button.setTextColor( bbf.titleColor )
        button.setBackgroundColor( bbf.backgroundColor )
        button.textSize = 23f
        return this
    }

}



class ButtonBuildFactory private constructor() {


    var backgroundColor : Int = 0
    var titleColor      : Int = 0

    lateinit var title : String


    companion object {
        fun build() : ButtonBuildFactory {
            return ButtonBuildFactory()
        }

        fun getDefaultFactory(context : Context) : ButtonBuildFactory {
            return ButtonBuildFactory
                        .build()
                        .setColorBackground( ContextCompat.getColor( context , R.color.blue_strong ) )
                        .setTitle( "Login" )
                        .setTitleColor( ContextCompat.getColor( context , android.R.color.white ) )
        }

    }
    fun setColorBackground(backgroundColor : Int) : ButtonBuildFactory {
        this.backgroundColor = backgroundColor
        return this
    }

    fun setTitleColor(titleColor : Int) : ButtonBuildFactory {
        this.titleColor = titleColor
        return this
    }

    fun setTitle(title : String): ButtonBuildFactory {
        this.title = title
        return this
    }

}
