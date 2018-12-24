package com.systemtechnology.devregister.utils

import android.content.Context
import android.net.Uri
import com.systemtechnology.devregister.entity.AddressEntity
import android.content.Intent



object UtilsIntentAction {

    fun openMapActivityBasedAddress(context : Context, addressEntity : AddressEntity ) {
        val gmmIntentUri = Uri.parse("geo:0,0?q=" +
            "${addressEntity.street.replace(" ","+")}+" +
            "${addressEntity.houseNumber.replace(" ", "+")}+" +
            "${addressEntity.city.replace(" ","+")}")

        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        context.startActivity(mapIntent)

    }
}
