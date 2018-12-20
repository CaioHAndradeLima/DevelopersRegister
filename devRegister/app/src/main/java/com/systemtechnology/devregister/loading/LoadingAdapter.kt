package com.systemtechnology.devregister.utils_adapters.loading

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.systemtechnology.devregister.R
import com.systemtechnology.devregister.define_rules.adapter.AdapterDependency

/**
 * create the layout loading
 *
 * @author Caio
 * @version 1
 * @since 1
 */
class LoadingAdapter(private val ad : AdapterDependency) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    /**
     * create the view holder
     *
     * @author Caio
     * @version 1
     * @since 1
     */
    override fun onCreateViewHolder(viewGroup : ViewGroup, position : Int): RecyclerView.ViewHolder {
        return LoadingHolder( ad.getLayoutInflater().inflate( R.layout.holder_loading , viewGroup , false ) )
    }

    /**
     * return the size of holders
     *
     * @author Caio
     * @version 1
     * @since 1
     *
     */
    override fun getItemCount(): Int {
        return 1
    }

    /**
     * notify the holder when needed
     *
     * @author Caio
     * @version 1
     * @since 1
     */
    override fun onBindViewHolder(holder : RecyclerView.ViewHolder, position : Int) {}

}
