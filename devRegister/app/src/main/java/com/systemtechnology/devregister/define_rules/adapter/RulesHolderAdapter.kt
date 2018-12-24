package com.systemtechnology.devregister.define_rules.adapter

import android.content.Context
import android.content.res.Resources
import android.support.v7.widget.RecyclerView
import android.view.View

import com.systemtechnology.devregister.define_rules.RulesBaseActivity
import com.systemtechnology.devregister.utils.DoubleClick

/**
 * here define life cycle holder functions
 *
 * @author Caio
 * @since 1
 */
abstract class RulesHolderAdapter(view : View) : RecyclerView.ViewHolder( view ) , HolderMethods {

    init {
        getReferences()
        setSettingsWhenExists()
    }

    protected fun getContext() : Context = itemView.context

    protected val doubleClick : DoubleClick
        get() { return (itemView.context as RulesBaseActivity).doubleClick!! }

    protected fun <T : View> findViewById(id : Int ) : T {
        return itemView.findViewById( id ) as T
    }

    override fun setSettingsWhenExists() {  }

    protected fun getString( id : Int ) : String = getContext().getString( id )

    protected fun getResources() : Resources = getContext().getResources()

}


interface HolderMethods {
    /**
     * get the references of view
     *
     * @author Caio
     * @version 1
     * @since 1
     */
    fun getReferences()

    /**
     * set the settings when exists
     *
     * @author Caio
     * @version 1
     * @since 7
     */
    fun setSettingsWhenExists()
    /**
     * notify when need change the data in layout
     *
     * @author Caio
     * @version 1
     * @since 1
     *
     * @param obj - the object that will update the layout
     *
     */
    fun bindViewHolder( obj : Any? )

}
