package com.systemtechnology.devregister.validate_helper

import com.systemtechnology.devregister.R
import com.systemtechnology.devregister.entity.DeveloperEntity
import com.systemtechnology.devregister.utils.UtilsValidate
import java.util.regex.Pattern

object DeveloperValidate {

    fun isValid(developerEntity : DeveloperEntity) : Int {
        if( developerEntity.name.length < 3 ||
            Pattern.matches("[a-zA-Z]+", developerEntity.name) ) {
            return R.string.form_client_error_name
        }

        if( developerEntity.CPF.length != 11 ) {
            return R.string.form_client_error_cpf
        }

        if( !developerEntity.addressIsInitialized() ) {
            return R.string.form_client_error_address
        }

        if( !UtilsValidate.isValidEmail( developerEntity.email ) ) {
            return R.string.form_client_error_email
        }

        if( developerEntity.password.length < 3 ) {
            return R.string.form_client_error_password_size
        }

        return 0
    }

}
