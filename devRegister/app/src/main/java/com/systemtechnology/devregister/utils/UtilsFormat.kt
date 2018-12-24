package com.systemtechnology.devregister.utils

import com.systemtechnology.devregister.entity.AddressEntity

object UtilsFormat {

    fun formatAddressToPutOnLayout(addressEntity : AddressEntity) : String {
        return "${addressEntity.street}, ${addressEntity.houseNumber}"
    }

}
