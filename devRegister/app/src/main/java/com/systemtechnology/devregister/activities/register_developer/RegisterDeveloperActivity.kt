package com.systemtechnology.devregister.activities.register_developer

import android.content.Intent
import android.graphics.Bitmap
import android.location.Address
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.widget.CardView
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
import com.systemtechnology.devregister.configs.ConfigActionsBroadManager
import com.systemtechnology.devregister.define_rules.AnyPresenter
import com.systemtechnology.devregister.define_rules.RulesBaseActivityBroadcasts
import com.systemtechnology.devregister.entity.AddressEntity
import com.systemtechnology.devregister.entity.DeveloperEntity
import com.systemtechnology.devregister.mask_helper.CpfCnpjMask
import com.systemtechnology.devregister.utils.UtilsIntentAction
import kotlinx.android.synthetic.main.activity_register_activity.view.*
import kotlinx.android.synthetic.main.activity_register_developer.*
import java.lang.IllegalStateException

class RegisterDeveloperActivity : RegisterDeveloperActivityView(),
                                                    RegisterDeveloperMethods {
    companion object {
        const val EXTRA_DEVELOPER           = "EXTRA_DEVELOPER"
        const val ACTION_DEVELOPER_REGISTER  = ConfigActionsBroadManager.ACTION_DEVELOPER_REGISTER
    }

    private fun getDeveloper() = (presenter as RegisterDeveloperPresenter).developer

    override fun whenDeveloperWasSalved(developerEntity: DeveloperEntity) {
        sendAction( developerEntity )
        finish()
    }

    private fun sendAction(developerEntity : DeveloperEntity  ) {

        val it = Intent( ACTION_DEVELOPER_REGISTER )

        it.putExtra( EXTRA_DEVELOPER , toJson( developerEntity ) )

        LocalBroadcastManager
            .getInstance( this )
            .sendBroadcast( it )
    }

    override fun getInstancePresenter(): AnyPresenter {
        return RegisterDeveloperPresenter(this)
    }

    override fun whenFormWrong(idMessage: Int) {
        openSnackBar( getString( idMessage ) )
    }

    override fun notifyIsInsertingNewDeveloper() {
        putMessagesOnLayout()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        UtilsIntentAction.openMapActivityBasedAddress( this , getDeveloper().addressEntity )
        return super.onOptionsItemSelected(item)
    }

    override fun onReceiv(intent: Intent) {
        if( intent.action == AddressConfirmActivity.ACTION_ADDRESS_COMPLETED ) {

            val jsonString = intent.getStringExtra( AddressConfirmActivityView.EXTRA_ADDRESS )

            (presenter as RegisterDeveloperPresenter).
                    whenReceiveAddress(
                        fromJson( jsonString )
                    )

            updateButtonAddress(
                (presenter as RegisterDeveloperPresenter)
                    .developer.addressEntity
            )

        } else {
            throw IllegalStateException()
        }
    }

    override fun userAlreadyChoseThePhoto() : Boolean {
        return ccp.userAlreadyChoseThePhoto()
    }


    override fun getBitmap(): Bitmap? {
        return ccp.createBitmap()
    }

}


abstract class RegisterDeveloperActivityView : RulesBaseActivityBroadcasts(), View.OnClickListener {

    private lateinit var editTextName   : EditText
    private lateinit var editTextCPF    : EditText
    private lateinit var editTextEmail   : EditText
    private lateinit var editTextPassword: EditText

    private lateinit var cardView     : CardView
    private lateinit var buttonConfirm: Button
    private lateinit var textViewTitle: TextView
    private lateinit var textViewPhoto: TextView
    protected lateinit var ccp          : CollapseChosePhoto

    override fun getLayoutResActivity(): Int {
        return R.layout.activity_register_developer
    }

    override fun getReferences() {
        editTextName    = findViewById( R.id.edit_text_name )
        editTextCPF     = findViewById( R.id.edit_text_cpf )
        cardView        = findViewById( R.id.card_view )
        textViewTitle   = findViewById( R.id.textview)
        buttonConfirm   = findViewById( R.id.button )
        textViewPhoto   = findViewById( R.id.text_view_photo)
        ccp             = findViewById( R.id.appbar )
        editTextEmail    = findViewById( R.id.edit_text_email )
        editTextPassword = findViewById( R.id.edit_text_password )
    }

    override fun setSettingsIfExists() {

        CpfCnpjMask.insertTextWatcher( editTextCPF )

        cardView.setOnClickListener( this )

        buttonConfirm.setOnClickListener {

            if( doubleClick!!.isSingleClick() )
                (presenter as RegisterDeveloperPresenter).whenClickedButtonConfirm(
                    currentName = editTextName.text.toString() ,
                    currentCPF = CpfCnpjMask.unmask( editTextCPF.text.toString() ) ,
                    currentEmail = editTextEmail.text.toString(),
                    currentPassword = editTextPassword.text.toString()
                )
        }

        ccp.notifyUseFloatingButtonTurnLeft(
            container
        )

        ccp.getTolbar().title = getString(R.string.form_register_dev_title_toolbar)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        ccp.onActivtyResultPhotoGallery( data , requestCode )
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

    protected fun updateButtonAddress(address : AddressEntity) {
        cardView.text_view_photo.text = address.street
    }

}
