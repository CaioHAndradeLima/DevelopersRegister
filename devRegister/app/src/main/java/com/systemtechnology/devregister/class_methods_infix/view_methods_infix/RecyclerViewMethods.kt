package com.systemtechnology.devregister.class_methods_infix.view_methods_infix

import android.support.v7.widget.RecyclerView
import com.systemtechnology.devregister.animation_view.AnimationViewFactory


infix fun RecyclerView.makeAnimationByIndex(index: Int) {
    post {
        AnimationViewFactory
            .create(
                findViewHolderForAdapterPosition( index )!!.itemView ,
                duration = 1000L
            )
    }
}