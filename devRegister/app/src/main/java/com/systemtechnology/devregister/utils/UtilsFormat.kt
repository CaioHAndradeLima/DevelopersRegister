package com.systemtechnology.devregister.utils

import com.systemtechnology.devregister.entity.Address

object UtilsFormat {

    fun formatAddressToPutOnLayout(address : Address) : String {
        return "${address.street}, ${address.houseNumber}"
    }

}
