package com.systemtechnology.design.components

import android.content.Context
import android.support.v7.widget.CardView
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import com.systemtechnology.design.R
import kotlinx.android.synthetic.main.card_view_button_with_icon.view.*


class CardViewButtonWithIcon : CardView {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    init {
        isClickable = true
        isFocusable = true

        val outValue = TypedValue()
        context.theme.resolveAttribute(android.R.attr.selectableItemBackground, outValue, true)
        setBackgroundResource( outValue.resourceId )

        preventCornerOverlap = false


        LayoutInflater
            .from( context )
            .inflate(
                R.layout.card_view_button_with_icon ,
                this ,
                true
            )
    }

    fun setIcon( idRes : Int ) {
        imageview.setImageResource( idRes )
    }

    fun setText( text : String ) {
        text_view_photo.text = text
    }



}
