package com.systemtechnology.devregister.activities.address_confirm

import android.content.Intent
import android.support.v4.content.LocalBroadcastManager
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.systemtechnology.devregister.entity.AddressEntity
import com.systemtechnology.devregister.R
import com.systemtechnology.devregister.configs.ConfigActionsBroadManager
import com.systemtechnology.devregister.define_rules.AnyPresenter
import com.systemtechnology.devregister.define_rules.RulesBaseActivity

class AddressConfirmActivity : AddressConfirmActivityView(), AddressConfirmMethods {

    companion object {
        const val ACTION_ADDRESS_COMPLETED = ConfigActionsBroadManager.ACTION_ADDRESS_COMPLETED
    }

    override fun whenNotValid(msg: Int) {
        openSnackBar( getString( msg ) )
    }

    override fun sendBroadcastAddress() {
        val it = Intent( ACTION_ADDRESS_COMPLETED )

        it.putExtra( EXTRA_ADDRESS , toJson( addressEntity ) )

        LocalBroadcastManager
            .getInstance( this )
            .sendBroadcast( it )

        finish()
    }

    override fun getInstancePresenter(): AnyPresenter {
        return AddressConfirmPresenter( this )
    }
}


abstract class AddressConfirmActivityView : RulesBaseActivity(), View.OnClickListener {

    companion object {
        const val EXTRA_ADDRESS = "EXTRA_ADDRESS"
    }

    private lateinit var edtStreet          : EditText
    private lateinit var edtComplementary   : EditText
    private lateinit var edtHomeNumber      : EditText

    private lateinit var button             : Button

    protected lateinit var addressEntity          : AddressEntity


    override fun getLayoutResActivity(): Int {
        return R.layout.activity_address_confirm
    }

    override fun getReferences() {
        edtStreet        = findViewById(R.id.edtStreet)
        edtHomeNumber    = findViewById(R.id.edtNumber)
        edtComplementary = findViewById(R.id.edtComplement)
        button           = findViewById(R.id.button)
    }

    override fun setSettingsIfExists() {
        val json = intent.getStringExtra( EXTRA_ADDRESS )

        addressEntity = fromJson( json , AddressEntity::class.java )

        toLayout()

        button.setOnClickListener( this )

    }

    override fun onClick( view : View) {
        if( doubleClick!!.isDoubleClick() ) return

        (presenter as AddressConfirmPresenter)
            .whenClickedConfirm(
                getNumberHome()     ,
                getComplementary()  ,
                addressEntity     )
    }

    private fun toLayout(){

        edtStreet.setText( "${addressEntity.street}, ${addressEntity.city}" )
        edtHomeNumber.setText(      addressEntity.houseNumber     )
        edtComplementary.setText(   addressEntity.complementary   )
    }

    private fun getNumberHome(): String {
        return edtHomeNumber.text.toString()
    }

    private fun getComplementary() : String {
        return edtComplementary.text.toString()
    }

}
