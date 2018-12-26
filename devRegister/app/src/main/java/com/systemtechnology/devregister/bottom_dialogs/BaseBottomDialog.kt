package com.systemtechnology.devregister.bottom_dialogs

import android.app.Dialog
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment

import android.view.View

abstract class BaseBottomDialog : BottomSheetDialogFragment() ,
                                  BaseBottomDialogMethods {


    protected lateinit var container : View

    companion object {

        @JvmStatic
        fun <T : BaseBottomDialog> newInstance( clazzType : Class<T> ,
                                                bundle    : Bundle ) : T {

            val bottomDialog = clazzType.newInstance()

            bottomDialog.arguments = bundle

            return bottomDialog
        }
    }


    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)

        container = View.inflate(   context,
                getResContentView() ,
                null
        )

        dialog.setContentView( container )

        container.rootView.minimumHeight = container.height

        getReferences()

        afterViewCreated()
    }

}



interface BaseBottomDialogMethods {
    fun getResContentView() : Int
    fun getReferences()
    fun afterViewCreated()
}