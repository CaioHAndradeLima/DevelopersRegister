package com.systemtechnology.devregister.utils

import android.content.Context
import android.net.Uri
import com.systemtechnology.devregister.entity.Address
import android.content.Intent



object UtilsIntentAction {

    fun openMapActivityBasedAddress( context : Context , address : Address ) {
        val gmmIntentUri = Uri.parse("geo:0,0?q=" +
            "${address.street.replace(" ","+")}+" +
            "${address.houseNumber.replace(" ", "+")}+" +
            "${address.city.replace(" ","+")}")

        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        context.startActivity(mapIntent)

    }
}
