package com.systemtechnology.devregister.activities.register_activity

import android.content.Intent
import android.support.v4.content.LocalBroadcastManager
import android.widget.EditText
import android.widget.TextView

import com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog

import com.systemtechnology.devregister.R
import com.systemtechnology.devregister.activities.register_developer.RegisterDeveloperActivity
import com.systemtechnology.devregister.bottom_dialog_date.BottomDialogDateFactory
import com.systemtechnology.devregister.configs.ConfigActionsBroadManager
import com.systemtechnology.devregister.define_rules.AnyPresenter
import com.systemtechnology.devregister.define_rules.RulesBaseActivity
import com.systemtechnology.devregister.entity.ActivityDevEntity
import com.systemtechnology.devregister.entity.DeveloperEntity

import com.systemtechnology.devregister.utils_date.UtilsDateFormat

import kotlinx.android.synthetic.main.activity_register_activity.*
import java.lang.IllegalStateException

class ActivityRegisterActivity : ActivityRegisterActivityView(),
                                 ActivityRegisterMethods {

    companion object {
        const val EXTRA_ACTIVITY_REGISTER_ENTITY = "EARE"

        const val ACTION_ACTIVITY_DEV = ConfigActionsBroadManager.ACTION_ACTIVITY_DEV

        const val EXTRA_ACTIVITY_REGISTER_ENTITY_INSERTING = "EAREI"

    }

    override fun getActivityDevIfExists() : ActivityDevEntity? {
        return fromJson(
            intent.getStringExtra( EXTRA_ACTIVITY_REGISTER_ENTITY )
        )
    }

    override fun getDeveloperEntity(): DeveloperEntity {
        return fromJson(
                    intent.getStringExtra( RegisterDeveloperActivity.EXTRA_DEVELOPER )
                )
    }

    override fun notifyUpdating( activityDevEntity : ActivityDevEntity ) {
        layoutToUpdating( activityDevEntity )
    }

    override fun notifyInserting() {
        layoutToInserting()
    }

    override fun whenSaveDevEntity(ade: ActivityDevEntity, isInserting : Boolean ) {
        val it = Intent( ACTION_ACTIVITY_DEV )
        it.putExtra( EXTRA_ACTIVITY_REGISTER_ENTITY , toJson( ade ) )
        it.putExtra( EXTRA_ACTIVITY_REGISTER_ENTITY_INSERTING , isInserting )

        LocalBroadcastManager
            .getInstance( this )
            .sendBroadcast( it )

        finish()
    }

    override fun whenFormError(resString: Int) {
        openSnackBar( getString( resString ) )
    }

    override fun getInstancePresenter() : AnyPresenter {
        return ActivityRegisterPresenter( this )
    }

}

abstract class ActivityRegisterActivityView : RulesBaseActivity() {

    private lateinit var bottomDialogDate : SingleDateAndTimePickerDialog

    private lateinit var txtTitle       : TextView

    private lateinit var txtCardViewButton : TextView

    private lateinit var edtRequester   : EditText
    private lateinit var edtDescription : EditText
    private lateinit var button         : TextView

    private var dateSelected : String? = null

    override fun getLayoutResActivity() : Int {
        return R.layout.activity_register_activity
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        inflateAppBarHeaderUser()
            .setToolbarTitle( getString( R.string.activity_register_activity_title ) )
            .setNavigationIcon( R.drawable.ic_activity_done )
    }

    override fun getReferences() {
        button = txt_button
        txtTitle = textview
        edtRequester = edit_text_requester
        edtDescription = edit_text_description
        txtCardViewButton = text_view_photo
    }

    override fun setSettingsIfExists() {
        card_view.setOnClickListener {
            if( doubleClick!!.isSingleClick() )
                openBottomDialogDate()
        }

        button.setOnClickListener {

            if( doubleClick!!.isSingleClick() )
                (presenter as ActivityRegisterPresenter)
                    .onClick(
                        edtRequester.text.toString() ,
                        edtDescription.text.toString() ,
                        dateSelected
                    )
        }
    }

    protected fun layoutToUpdating( ade : ActivityDevEntity ) {
        txtTitle.text = getString(R.string.activity_register_activity_form_title_updating)
        button.text   = getString(R.string.activity_register_activity_form_button_updating)
        edtRequester.setText( ade.requester )
        edtRequester.isEnabled = false

        setNewDateOnCardView( ade.dateToDelivery )
        edtDescription.setText( ade.description )
    }

    protected fun layoutToInserting() {
        txtTitle.text = getString(R.string.activity_register_activity_form_title)
        button.text   = getString(R.string.activity_register_activity_form_button)
    }

    private fun openBottomDialogDate() {

        if( !::bottomDialogDate.isInitialized ) {
            bottomDialogDate = BottomDialogDateFactory.build( this ) {
                dateSelected = UtilsDateFormat.format( it )

                setNewDateOnCardView( dateSelected!! )

            }
        }

        bottomDialogDate.display()
    }

    private fun setNewDateOnCardView(dateSelected: String) {
        txtCardViewButton.setTextColor( getColorCompat( android.R.color.holo_green_dark ) )
        txtCardViewButton.textSize = 20F
        txtCardViewButton.text = "${getString( R.string.delivery_at )} $dateSelected"
    }

    private fun closeBottomDialogDate() {
        bottomDialogDate.dismiss()
    }

    override fun onBackPressed() {

        if( ::bottomDialogDate.isInitialized && bottomDialogDate.isDisplaying ) {
            closeBottomDialogDate()
            return
        }

        super.onBackPressed()
    }
}