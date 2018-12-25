package com.systemtechnology.devregister.define_rules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import caiohenrique.auxphoto.AuxiliarPhoto
import com.systemtechnology.design.components.AppBarHeaderUser
import com.systemtechnology.devregister.R
import com.systemtechnology.devregister.activities.register_developer.RegisterDeveloperActivity
import com.systemtechnology.devregister.define_rules.adapter.AdapterDependency
import com.systemtechnology.devregister.entity.DeveloperEntity
import com.systemtechnology.devregister.utils.DoubleClick
import com.systemtechnology.devregister.utils.UtilsConvertJson
import com.systemtechnology.devregister.utils.UtilsLoaderPhoto
import com.systemtechnology.devregister.viewmodel.DeveloperViewModel
import kotlinx.android.synthetic.main.activity_dev_details.*

abstract class RulesBaseActivity : AppCompatActivity(),
                                    AdapterDependency,
                                    ActivityMethods {

    /**
     * the presenter that management view
     */
    lateinit var presenter: AnyPresenter

    /**
     * here decides what the actions needed be called in every activity
     *
     * @author Caio
     * @version 1
     * @since 1
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResActivity())

        getReferences()
        setSettingsIfExists()

        presenter = getInstancePresenter()

        presenter.onCreate()
    }


    /**
     * when destroy the activity
     *
     * @author Caio
     * @version 1
     * @since 1
     */
    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    /**
     * open the snack bar with a message
     *
     * @author Caio
     * @version 1
     * @since 1
     */
    protected fun openSnackBar(text: String) {
        Snackbar.make(findViewById(R.id.container), text, Snackbar.LENGTH_LONG).show()
    }


    /**
     * verify if was double click
     */
    var doubleClick : DoubleClick? = null
        get() {

        if ( field == null ) {
            field = DoubleClick()
        }

        return field
    }


    protected fun toJson(obj : Any) : String {
        return UtilsConvertJson.toJson( obj )
    }

    protected fun <T>fromJson( json : String?, obj : Class<T> ) : T{
        return UtilsConvertJson.fromJson( json , obj )
    }

    protected fun getColorCompat( color : Int ) : Int {
        return ContextCompat.getColor( this , color )
    }

    fun <T : ViewModel> getViewModel( clazz : Class<T> ) : T {
        return ViewModelProviders.of( this ).get( clazz )
    }

    fun loadPhotoFromStorage(imageView: ImageView,
                             directory : String,
                             nameImage : String,
                             auxPhoto  : AuxiliarPhoto =
                                 AuxiliarPhoto( this ) ) {
        presenter
            .addDisposable(
                UtilsLoaderPhoto.loadPhotoFromStorage(
                    imageView ,
                    directory ,
                    nameImage,
                    auxPhoto
                )
            )

    }


    protected fun inflateAppBarHeaderUser() : AppBarHeaderUser {
        return AppBarHeaderUser
                    .create(
                        this ,
                        container ,
                        getViewModel( DeveloperViewModel::class.java )
                            .setDeveloper( fromJson(
                                intent.getStringExtra( RegisterDeveloperActivity.EXTRA_DEVELOPER ) ,
                                DeveloperEntity::class.java
                                )
                            )
                    )
    }




}

/**
 * methods of every activity
 *
 * @author Caio
 */
interface ActivityMethods {

    /**
     * get the layout that will to inflate
     *
     * @author Caio
     * @version 1
     * @since 1
     */
    @LayoutRes
    fun getLayoutResActivity(): Int


    /**
     * get the views references on layout
     *
     * @author Caio
     * @version 1
     * @since 1
     */
    fun getReferences()

    /**
     * get the instance presenter
     *
     * @author Caio
     * @version 1
     * @since 1
     */
    fun getInstancePresenter(): AnyPresenter

    /**
     * set the settings on layout when exists
     *
     * @author Caio
     * @version 1
     * @since 1
     */
    fun setSettingsIfExists()
}

