package com.systemtechnology.design.components

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Matrix
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.systemtechnology.design.IntentPhotoUtils
import com.systemtechnology.design.R
import kotlinx.android.synthetic.main.component_collapse_chose_photo.view.*
import kotlinx.android.synthetic.main.component_collapse_floating_turn_left.view.*

class CollapseChosePhoto( context: Context ,
                          attrs : AttributeSet? ) : AppBarLayout(context , attrs ) ,
                                                    OnActivityResultListener {

    private lateinit var bitmap : Bitmap

    init {
        LayoutInflater
            .from( context )
            .inflate( R.layout.component_collapse_chose_photo , this , true )


        setSettings()

    }

    private fun setSettings() {
        imageview.setOnClickListener { _ ->
            IntentPhotoUtils.openGallery( context as Activity )
        }
    }

    override fun onActivtyResultPhotoGallery( data : Intent) : Bitmap {
        return IntentPhotoUtils.
                    decodeIntentDataToBitmapAfterUserSelectPhoto( context , data)
    }

    fun notifyUseFloatingButtonTurnLeft( container : CoordinatorLayout ) : CollapseChosePhoto {
        LayoutInflater
            .from( context )
            .inflate(   R.layout.component_collapse_floating_turn_left ,
                        container ,
                        true )

        fab.setOnClickListener{
            whenFabButtonTurnLeftClicked()
        }

        return this
    }

    private fun whenFabButtonTurnLeftClicked() {
        imageview.rotation = imageview.rotation + 90
        imageview.layoutParams = imageview.layoutParams
    }

    fun createBitmap() : Bitmap {
        // recreate the new Bitmap
        if (imageview.getRotation() == 0f)
            return bitmap

        val matrix = Matrix()
        matrix.setRotate(imageview.getRotation())

        return Bitmap.createBitmap(
            bitmap,
            0,
            0,
            bitmap.width,
            bitmap.height,
            matrix,
            true
        )
    }


    fun userAlreadyChoseThePhoto() : Boolean {
        return ::bitmap.isInitialized
    }

}


interface OnActivityResultListener {

    fun onActivtyResultPhotoGallery( data : Intent ) : Bitmap
}