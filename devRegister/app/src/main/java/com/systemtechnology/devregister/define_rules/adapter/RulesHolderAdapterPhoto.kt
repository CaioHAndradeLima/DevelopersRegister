package com.systemtechnology.devregister.define_rules.adapter

import android.view.View
import android.widget.ImageView
import com.systemtechnology.design.utils.UtilsLoaderPhoto

abstract class RulesHolderAdapterPhoto(view: View) : RulesHolderAdapter(view) {

    private val auxPhoto = caiohenrique
                            .auxphoto
                            .AuxiliarPhoto( itemView.context )

    protected fun loadPhotoFromStorage(imageView: ImageView,
                                       directory : String,
                                       nameImage : String) {

        UtilsLoaderPhoto.loadPhotoFromStorage(
            imageView,
            directory ,
            nameImage ,
            auxPhoto
        )
    }

}
