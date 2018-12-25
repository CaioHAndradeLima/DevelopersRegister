package com.systemtechnology.devregister.viewmodel

import android.arch.lifecycle.ViewModel
import android.widget.ImageView
import com.systemtechnology.design.components.AppBarHeaderUser
import com.systemtechnology.devregister.configs.ConfigDirectory
import com.systemtechnology.devregister.entity.DeveloperEntity
import com.systemtechnology.devregister.utils.UtilsLoaderPhoto

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
        return "${developer.listActivityDev.size} atividades agora"
    }

    override fun loadPhoto(imageView: ImageView) {
        UtilsLoaderPhoto
            .loadPhotoFromStorage( imageView ,
                                   ConfigDirectory.DIRECTORY_DEVELOPERS ,
                                    developer.CPF )
    }

}