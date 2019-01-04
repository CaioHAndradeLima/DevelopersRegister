package com.systemtechnology.design.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.ImageView
import caiohenrique.auxphoto.AuxiliarPhoto
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.io.File
import java.io.FileInputStream

object UtilsLoaderPhoto {

    fun loadPhotoFromStorage(imageView: ImageView,
                             directory: String,
                             nameImage: String,
                             auxPhoto: AuxiliarPhoto =

                                     AuxiliarPhoto(imageView.context)
                                ): Disposable {

        return Observable
                .just(auxPhoto)
                .map {
                    it.loadImageFromStorage(
                            directory,
                            nameImage
                    )
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.computation())
                .subscribe { imageView.setImageBitmap(it) }

    }

    fun loadBitmapFromInternalPath( path : String ,
                                    auxPhoto : AuxiliarPhoto ) : Observable<Bitmap> {

         return Observable
                .just(path)
                .map { File(it) }
                .filter { it.exists() }
                 .doOnNext {
                     val filter = it.length() > SIZE_MAX
                     if( filter ) {
                         Observable.just( auxPhoto.modifyScaleOfFile( it , 270 ) )
                     }
                 }
                .map { FileInputStream(it) }
                .map { BitmapFactory.decodeStream(it) }
        x
    }

    fun loadPhotoFromInternalPath(imageView: ImageView, path: String): Disposable {
        return loadBitmapFromInternalPath( path )
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.computation())
                    .subscribe ({ imageView?.setImageBitmap(it) }) {
                        it.printStackTrace()

                    }

    }

    const val ONE_MEGA = 1024000
    const val SIZE_MAX = ONE_MEGA / 5

}