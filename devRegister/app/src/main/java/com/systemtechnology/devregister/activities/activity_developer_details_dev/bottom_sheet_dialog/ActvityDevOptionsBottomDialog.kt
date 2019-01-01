package com.systemtechnology.devregister.activities.activity_developer_details_dev.bottom_sheet_dialog

import android.content.Intent
import com.systemtechnology.devregister.bottom_dialogs.OptionsBottomSheetEntity
import com.systemtechnology.devregister.bottom_dialogs.OptionsRecyclerBottomDialog
import com.systemtechnology.devregister.entity.ActivityDevEntity
import com.systemtechnology.devregister.entity.infix_methods.toJson

class ActvityDevOptionsBottomDialog : OptionsRecyclerBottomDialog() {

    companion object {
        const val ACTIVITY_DEV_ENTITY = "EXTRA_AC_DEV_ENTITY"
    }

    fun setActivityDev( ade : ActivityDevEntity ) : ActvityDevOptionsBottomDialog {
        arguments!!.putString( ACTIVITY_DEV_ENTITY , ade.toJson() )
        return this
    }

    override fun createIntentBroadcast(bottomDialogEntity: OptionsBottomSheetEntity): Intent {
        val it = super.createIntentBroadcast(bottomDialogEntity)

        it.putExtra( ACTIVITY_DEV_ENTITY , arguments!!.getString( ACTIVITY_DEV_ENTITY ) )

        return it
    }
}
