package com.systemtechnology.devregister.viewmodel

import android.arch.lifecycle.ViewModel
import android.widget.ImageView
import com.systemtechnology.design.components.AppBarHeaderUser
import com.systemtechnology.design.utils.UtilsLoaderPhoto
import com.systemtechnology.devregister.configs.ConfigDirectory
import com.systemtechnology.devregister.entity.DeveloperEntity

class DeveloperViewModel : ViewModel() , AppBarHeaderUser.AppBarHeaderUserMethods {

    lateinit var developer : DeveloperEntity
    private set

    fun setDeveloper(developer : DeveloperEntity) : DeveloperViewModel {
        this.developer = developer

        return this
    }

    override fun getTitle() : String {
        return developer.name
    }

    override fun getSubtitle(): String {

        if( developer.listActivityDev.isEmpty() ) {
            return "Ainda não possui atividades"
        }

        val quantity = developer.quantityActivityDevExecuting()

        if( quantity == 0 ) {
            return "Nenhuma atividade agora"
        } else {
            return "$quantity atividades agora"
        }

    }

    override fun loadPhoto(imageView: ImageView) {
        UtilsLoaderPhoto
            .loadPhotoFromStorage( imageView ,
                                   ConfigDirectory.DIRECTORY_DEVELOPERS ,
                                    developer.CPF )
    }

}