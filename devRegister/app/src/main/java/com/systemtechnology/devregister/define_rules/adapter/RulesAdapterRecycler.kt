package com.systemtechnology.devregister.define_rules.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import com.systemtechnology.devregister.animation_view.AnimationViewFactory

/**
 * apply the rules for any adapter,
 * in other words, he means what's need
 * to have for it work
 *
 * @author Caio
 * @version 1
 * @since 1
 *
 * @param list list of object
 * @param ad   the dependency needed
 */
abstract class RulesAdapterRecycler<T>( protected var list : List<T>?,
                                        private val ad : AdapterDependency) :
                                            RecyclerView.Adapter<RecyclerView.ViewHolder>() ,
                                            AdapterMethods<T> {


    protected val layoutInflater : LayoutInflater
        get() {
            return ad.getLayoutInflater()
        }

    /**
     * get size of recycler view
     *
     * @author Caio
     * @version 1
     * @since 1
     */
    override fun getItemCount(): Int {
        return list!!.size
    }

    /**
     * notify the holder that need change the data on layout
     *
     * @author Caio
     * @version 1
     * @since 1
     */
    override fun onBindViewHolder(holder : RecyclerView.ViewHolder, position : Int) {
        (holder as HolderMethods).bindViewHolder( getCurrentObject( position ) as Any )
    }


    /**
     * get the current object based at position
     *
     * @author Caio
     * @version 1
     * @since 1
     */
    override fun getCurrentObject(position : Int ) : T {
        return list!![ position ]
    }
}

internal interface AdapterMethods<T> {
    fun getCurrentObject(position : Int ) : T
}


/**
 * contains what is need for to work
 * any adapter
 *
 * @author Caio
 * @version 1
 * @since 1
 */
interface AdapterDependency {

    /**
     * get the layout inflater
     * object that create the views
     *
     * @author Caio
     * @version 1
     * @since 1
     */
    fun getLayoutInflater() : LayoutInflater
}
