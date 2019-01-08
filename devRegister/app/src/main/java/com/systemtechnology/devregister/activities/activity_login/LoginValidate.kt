package com.systemtechnology.devregister.activities.activity_login

import com.systemtechnology.devregister.R
import com.systemtechnology.devregister.utils.UtilsValidate
import com.systemtechnology.devregister.validate_helper.validate_cpf_cnpj.CpfCnpjValidate

object LoginValidate {

    fun validate( le : LoginEntity ): Int {

        if( le.password.isEmpty() ) {
            return R.string.activity_login_form_error_password
        }


        return if( UtilsValidate.isValidEmail( le.emailOrCPF ) ||
            CpfCnpjValidate.isValidCPF( le.emailOrCPF ) ) {
            0
        } else {
            R.string.activity_login_form_error_email_or_cpf
        }


    }

}
