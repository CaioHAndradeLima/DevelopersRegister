package com.systemtechnology.devregister.define_rules.adapter

import android.view.View
import android.widget.ImageView
import com.systemtechnology.devregister.define_rules.RulesBaseActivity
import com.systemtechnology.devregister.utils.UtilsLoaderPhoto
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class RulesHolderAdapterPhoto(view: View) : RulesHolderAdapter(view) {

    private val auxPhoto = caiohenrique
                            .auxphoto
                            .AuxiliarPhoto( itemView.context )

    protected fun loadPhotoFromStorage(imageView: ImageView,
                                       directory : String,
                                       nameImage : String) {


        (itemView.context as RulesBaseActivity)
            .presenter
            .addDisposable(
                UtilsLoaderPhoto.loadPhotoFromStorage(
                    imageView ,
                    directory ,
                    nameImage,
                    auxPhoto
                )
            )
    }

}
