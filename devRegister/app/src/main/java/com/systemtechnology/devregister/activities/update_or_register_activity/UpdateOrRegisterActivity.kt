package com.systemtechnology.devregister.activities.update_or_register_activity

import android.content.Intent
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.widget.CardView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.systemtechnology.design.components.CollapseChosePhoto
import com.systemtechnology.devregister.R
import com.systemtechnology.devregister.activities.address_confirm.AddressConfirmActivity
import com.systemtechnology.devregister.activities.address_confirm.AddressConfirmActivityView
import com.systemtechnology.devregister.activities.get_address_by_cep.GetAddressByCepActivity
import com.systemtechnology.devregister.define_rules.PresenterAny
import com.systemtechnology.devregister.define_rules.RulesBaseActivityBroadcasts
import com.systemtechnology.devregister.entity.Address
import com.systemtechnology.devregister.entity.Developer
import com.systemtechnology.devregister.mask_helper.CpfCnpjMask
import com.systemtechnology.devregister.utils.UtilsIntentAction
import java.lang.IllegalStateException

class UpdateOrRegisterActivity : UpdateOrRegisterActivityView(),
                                 UpdateOrRegisterMethods {
    companion object {
        const val EXTRA_CLIENT           = "EXTRA_CLIENT"
        const val ACTION_CLIENT_REGISTER = "ACTION_CLIENT_REGISTER"
    }

    private fun getDeveloper() = (presenter as UpdateOrRegisterPresenter).developer

    override fun whenDeveloperWasSalved(developer: Developer) {
        sendAction( developer )
        finish()
    }

    private fun sendAction(developer : Developer  ) {

        val it = Intent( ACTION_CLIENT_REGISTER )

        it.putExtra( EXTRA_CLIENT , toJson( developer ) )

        LocalBroadcastManager
            .getInstance( this )
            .sendBroadcast( it )
    }

    override fun getInstancePresenter(): PresenterAny {
        return UpdateOrRegisterPresenter(this)
    }

    override fun whenFormWrong(idMessage: Int) {
        openSnackBar( getString( idMessage ) )
    }

    override fun notifyIsInsertingNewDeveloper() {
        putMessagesOnLayout()
    }

    override fun onCreateOptionsMenu( menu : Menu ) : Boolean {
        menuInflater.inflate( R.menu.menu_update_or_register_activity , menu )
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        UtilsIntentAction.openMapActivityBasedAddress( this , getDeveloper().address )
        return super.onOptionsItemSelected(item)
    }

    override fun onReceiv(intent: Intent) {
        if( intent.action == AddressConfirmActivity.ACTION_ADDRESS_COMPLETED ) {

            val jsonString = intent.getStringExtra( AddressConfirmActivityView.EXTRA_ADDRESS )

            (presenter as UpdateOrRegisterPresenter).
                    whenReceiveAddress(
                        fromJson( jsonString ,  Address::class.java )
                    )
        } else {
            throw IllegalStateException()
        }
    }
}


abstract class UpdateOrRegisterActivityView : RulesBaseActivityBroadcasts(), View.OnClickListener {

    private lateinit var editTextName : EditText
    private lateinit var editTextCPF  : EditText
    private lateinit var cardView     : CardView
    private lateinit var buttonConfirm: Button
    private lateinit var textViewTitle: TextView
    private lateinit var textViewPhoto: TextView
    private lateinit var ccp          : CollapseChosePhoto

    override fun getLayoutResActivity(): Int {
        return R.layout.activity_update_or_register
    }

    override fun getReferences() {
        editTextName    = findViewById( R.id.edit_text_name )
        editTextCPF     = findViewById( R.id.edit_text_cpf )
        cardView        = findViewById( R.id.card_view )
        textViewTitle   = findViewById( R.id.textview)
        buttonConfirm   = findViewById( R.id.button )
        textViewPhoto   = findViewById( R.id.text_view_photo)
        ccp             = findViewById( R.id.appbar )
    }

    override fun setSettingsIfExists() {

        CpfCnpjMask.insertTextWatcher( editTextCPF )

        cardView.setOnClickListener( this )

        buttonConfirm.setOnClickListener {

            if( doubleClick!!.isSingleClick() )
                (presenter as UpdateOrRegisterPresenter).whenClickedButtonConfirm(
                    editTextName.text.toString() ,
                    CpfCnpjMask.unmask( editTextCPF.text.toString() )
                )
        }
    }

    override fun getActions(): Array<String>? {
        return arrayOf( AddressConfirmActivity.ACTION_ADDRESS_COMPLETED )
    }

    override fun onClick( cardView : View ) {
        if( doubleClick!!.isSingleClick() )
            startActivity( Intent( this , GetAddressByCepActivity::class.java ) )
    }

    protected fun putMessagesOnLayout() {
        textViewTitle.setText( R.string.update_register_inserting_title )
        textViewPhoto.setText( R.string.update_register_inserting_photo )
        buttonConfirm.setText( R.string.update_register_inserting_button )

    }

}
