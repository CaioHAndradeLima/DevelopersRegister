package com.systemtechnology.devregister.utils

import android.widget.ImageView
import caiohenrique.auxphoto.AuxiliarPhoto
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

object UtilsLoaderPhoto {


    fun loadPhotoFromStorage(imageView: ImageView,
                             directory : String,
                             nameImage : String,
                             auxPhoto  : AuxiliarPhoto =
                                                AuxiliarPhoto( imageView.context )
                            ) : Disposable {

            return Observable
                        .just( auxPhoto )
                        .map {
                            it.loadImageFromStorage(
                                directory,
                                nameImage
                            )
                        }
                        .observeOn( AndroidSchedulers.mainThread() )
                        .subscribeOn( Schedulers.computation() )
                        .subscribe { imageView.setImageBitmap( it ) }

    }

}
