package com.systemtechnology.devregister.define_rules

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.systemtechnology.devregister.R
import com.systemtechnology.devregister.define_rules.adapter.AdapterDependency
import com.systemtechnology.devregister.utils.DoubleClick
import com.systemtechnology.devregister.utils.UtilsConvertJson

abstract class RulesBaseActivity : AppCompatActivity(),
                                    AdapterDependency,
                                    ActivityMethods {

    /**
     * the presenter that management view
     */
    lateinit var presenter: PresenterAny

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
    fun getInstancePresenter(): PresenterAny

    /**
     * set the settings on layout when exists
     *
     * @author Caio
     * @version 1
     * @since 1
     */
    fun setSettingsIfExists()
}

