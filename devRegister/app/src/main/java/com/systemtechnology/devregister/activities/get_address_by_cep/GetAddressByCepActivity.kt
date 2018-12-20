package com.systemtechnology.devregister.activities.get_address_by_cep

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.EditText
import br.systemtechnology.commerce.activities.get_address_by_cep.helper.ListenerWhenEditTextArrived8characters
import br.systemtechnology.commerce.activities.get_address_by_cep.helper.TextWatcherCepListener
import com.systemtechnology.devregister.R
import com.systemtechnology.devregister.activities.address_confirm.AddressConfirmActivity
import com.systemtechnology.devregister.activities.address_confirm.AddressConfirmActivityView
import com.systemtechnology.devregister.activities.get_address_by_cep.adapter.GetAddressByCepAdapter
import com.systemtechnology.devregister.define_rules.PresenterAny
import com.systemtechnology.devregister.define_rules.RulesBaseActivity
import com.systemtechnology.devregister.entity.Address
import com.systemtechnology.devregister.utils_adapters.loading.LoadingAdapter

class GetAddressByCepActivity : GetAddressByCepActivityView() ,
                                GetAddressByCepMethods {

    override fun whenAddressArrived(address: Address) {
        val it = Intent( this , AddressConfirmActivity::class.java )
        it.putExtra( AddressConfirmActivityView.EXTRA_ADDRESS , toJson( address ) )
        startActivity( it )
        finish()
    }

    override fun whenCepWrong(idMessage: Int) {
        openSnackBar( getString( idMessage ) )
    }

    override fun notifyErrorSearchAddress(idMessage: Int) {
        openSnackBar( getString( idMessage ) )
        changeAdapterAddress()
    }

    override fun notifyGoingSearchAddress() {
        changeAdapterLoading()
    }

    override fun whenEditTextArrived8characters() {
        (presenter as GetAddressByCepPresenter)
            .whenNeedSearch( getCurrentCep() )
    }

    override fun getInstancePresenter() : PresenterAny {
        return GetAddressByCepPresenter( this )
    }


}

abstract class GetAddressByCepActivityView : RulesBaseActivity(),
                                             ListenerWhenEditTextArrived8characters, View.OnClickListener {

    private lateinit var imageViewSearch : View
    private lateinit var edtCEP          : EditText

    private lateinit var recyclerView    : RecyclerView

    private val adapterGetAddressByCEP = GetAddressByCepAdapter( this )
    private val adapterLoading         = LoadingAdapter(this)

    override fun getLayoutResActivity() : Int {
        return R.layout.activity_get_address_by_cep
    }

    override fun getReferences() {
        imageViewSearch  = findViewById(R.id.imageview_search)
        edtCEP           = findViewById(R.id.edt_cep)
        recyclerView     = findViewById(R.id.recyclerview)
    }


    override fun setSettingsIfExists() {
        recyclerView.layoutManager = LinearLayoutManager( this )
        recyclerView.adapter       = adapterGetAddressByCEP

        imageViewSearch.setOnClickListener( this )

        edtCEP.addTextChangedListener( TextWatcherCepListener( this ) )

    }

    override fun onClick( imageViewToUser : View) {
        if(doubleClick!!.isSingleClick())
            (presenter as GetAddressByCepPresenter)
                .whenClickedImageView( getCurrentCep() )
    }

    protected fun getCurrentCep() : String {
        return edtCEP.text.toString()
    }

    protected fun changeAdapterLoading() {
        recyclerView.adapter = adapterLoading
    }

    protected fun changeAdapterAddress() {
        recyclerView.adapter = adapterGetAddressByCEP
    }

}