package com.systemtechnology.devregister.validate_helper

import com.systemtechnology.devregister.R
import com.systemtechnology.devregister.entity.Developer
import java.util.regex.Pattern

object ClientValidate {

    fun isValid(developer : Developer) : Int {
        if( developer.name.length < 10 ||
            Pattern.matches("[a-zA-Z]+", developer.name) ) {
            return R.string.form_client_error_name
        }

        if( developer.CPF.length != 11 ) {
            return R.string.form_client_error_cpf
        }

        if( !developer.addressIsInitialized() ) {
            return R.string.form_client_error_address
        }

        return 0
    }

}
