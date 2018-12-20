package com.systemtechnology.devregister.utils_adapters.loading

import android.view.View
import android.widget.ProgressBar
import com.systemtechnology.devregister.R
import com.systemtechnology.devregister.define_rules.adapter.RulesHolderAdapter

/**
 * holder loading
 *
 * @author Caio
 * @version 1
 * @since 1
 */
class LoadingHolder( view : View) : RulesHolderAdapter( view ) {
    /**
     * the progress bar view of layout
     *
     * @author Caio
     * @version 1
     * @since 1
     */
    private lateinit var progressBar : ProgressBar

    /**
     * get reference of layout
     *
     * @author Caio
     * @version 1
     * @since 1
     */
    override fun getReferences() {
        progressBar = findViewById(R.id.progressbar)
    }

    /**
     * put the data in layout when needed
     *
     * @author Caio
     * @version 1
     * @since 1
     * @date 18/10/2018
     */
    override fun bindViewHolder(obj: Any?) {
        throw IllegalAccessError("not exists update on this holder")
    }

}
