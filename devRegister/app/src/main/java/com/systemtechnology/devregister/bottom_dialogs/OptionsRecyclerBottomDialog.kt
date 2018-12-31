package com.systemtechnology.devregister.bottom_dialogs

import android.content.Intent
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.systemtechnology.devregister.R
import com.systemtechnology.devregister.configs.ConfigActionsBroadManager
import com.systemtechnology.devregister.define_rules.adapter.AdapterDependency
import com.systemtechnology.devregister.define_rules.adapter.RulesAdapterRecycler
import com.systemtechnology.devregister.define_rules.adapter.RulesHolderAdapter
import com.systemtechnology.devregister.utils.UtilsConvertJson
import io.reactivex.Observable



interface OptionsRecyclerBottomMethods : AdapterDependency {
    fun whenHolderClicked(bottomDialogEntity : OptionsBottomSheetEntity)
    fun createIntentBroadcast(bottomDialogEntity: OptionsBottomSheetEntity) : Intent
}

open class OptionsRecyclerBottomDialog : BaseBottomDialog(),
                                    OptionsRecyclerBottomMethods {

    private lateinit var recyclerView : RecyclerView
    private lateinit var listOptions : MutableList<OptionsBottomSheetEntity>

    companion object {
        private const val EXTRA_ARGUMENT_LIST = "EXTRA_ARG_LIST"

        const val ACTION_OPTION_CLICKED = ConfigActionsBroadManager.ACTION_OPTION_CLICKED
        const val EXTRA_BOTTOM_DIALOG_ENTITY = "EXTRA_BOTTOM_D_E"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Observable
            .empty<Bundle>()
            .filter { savedInstanceState != null }
            .map { arguments!!.getString( EXTRA_ARGUMENT_LIST ) }
            .map { fromJson<Array<OptionsBottomSheetEntity>>( it ) }
            .map { it.toMutableList() }
            .doOnNext { listOptions = it }
            .subscribe()
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

    override fun whenHolderClicked(bottomDialogEntity: OptionsBottomSheetEntity) {
        sendBroadcast( bottomDialogEntity )
        dismiss()
    }

    private fun sendBroadcast(bottomDialogEntity: OptionsBottomSheetEntity) {

        LocalBroadcastManager
            .getInstance( context!! )
            .sendBroadcast( createIntentBroadcast( bottomDialogEntity ) )
    }

    override fun createIntentBroadcast(bottomDialogEntity: OptionsBottomSheetEntity) : Intent {
        val it = Intent( ACTION_OPTION_CLICKED )
        it.putExtra( EXTRA_BOTTOM_DIALOG_ENTITY , toJson( bottomDialogEntity ) )
        return it
    }

}

class OptionsBottomSheetAdapter( list : List<OptionsBottomSheetEntity> ,
                                 private val orbm : OptionsRecyclerBottomMethods)

                        : RulesAdapterRecycler<OptionsBottomSheetEntity>( list , orbm ) {

    override fun onCreateViewHolder( container : ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return OptionsBottomSheetHolder(
                layoutInflater.inflate( R.layout.holder_options_dialog , container , false ) ,
                orbm
        )
    }
}

data class OptionsBottomSheetEntity( val drawableRes : Int ,
                                     val text        : String,
                                     val id          : String)


class OptionsBottomSheetHolder( view : View ,
                                private val methods : OptionsRecyclerBottomMethods )

                                                : RulesHolderAdapter( view ),
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
        methods.whenHolderClicked( bottomDialogEntity )
    }


}
