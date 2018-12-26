package com.systemtechnology.devregister.bottom_dialogs

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.systemtechnology.devregister.R
import com.systemtechnology.devregister.define_rules.adapter.AdapterDependency
import com.systemtechnology.devregister.define_rules.adapter.RulesAdapterRecycler
import com.systemtechnology.devregister.define_rules.adapter.RulesHolderAdapter
import com.systemtechnology.devregister.utils.UtilsConvertJson

class OptionsRecyclerBottomDialog : BaseBottomDialog(), AdapterDependency {

    private lateinit var recyclerView : RecyclerView

    private lateinit var listOptions : MutableList<OptionsBottomSheetEntity>

    companion object {
        const val EXTRA_ARGUMENT_LIST = "EXTRA_ARG_LIST"
    }

    override fun getResContentView(): Int {
        return R.layout.bottom_sheet_toolbar_recycler
    }

    override fun getReferences() {
        recyclerView = container.findViewById( R.id.recyclerview )
    }

    override fun afterViewCreated() {
        recyclerView.layoutManager = LinearLayoutManager( context )
        recyclerView.adapter = OptionsBottomSheetAdapter( listOptions , this)
    }

    fun setListOptions( list : MutableList<OptionsBottomSheetEntity> ) : OptionsRecyclerBottomDialog {
        arguments!!.putString( EXTRA_ARGUMENT_LIST , UtilsConvertJson.toJson( list ) )

        listOptions = list

        return this
    }

}

class OptionsBottomSheetAdapter( list : List<OptionsBottomSheetEntity> ,
                                 ad   : AdapterDependency)
    : RulesAdapterRecycler<OptionsBottomSheetEntity>( list , ad ) {

    override fun onCreateViewHolder( container : ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return OptionsBottomSheetHolder(
                layoutInflater.inflate( R.layout.holder_options_dialog , container , false )
        )
    }
}


data class OptionsBottomSheetEntity( val drawableRes : Int ,
                                     val text        : String ,
                                     val listener    : ( view : View ) -> Unit )


class OptionsBottomSheetHolder( view : View ) : RulesHolderAdapter( view ),
                                                View.OnClickListener {

    private lateinit var imageView : ImageView
    private lateinit var txtMessage : TextView

    private lateinit var bottomDialogEntity : OptionsBottomSheetEntity

    override fun getReferences() {
        txtMessage = findViewById( R.id.textview_title )
        imageView  = findViewById( R.id.imageview )
    }

    override fun setSettingsWhenExists() {
        itemView.setOnClickListener( this )
    }

    override fun bindViewHolder(obj: Any?) {
        bottomDialogEntity = obj!! as OptionsBottomSheetEntity

        imageView.setImageResource( bottomDialogEntity.drawableRes )
        txtMessage.text = bottomDialogEntity.text
    }

    override fun onClick(v: View) {
        bottomDialogEntity.listener( v )
    }


}
