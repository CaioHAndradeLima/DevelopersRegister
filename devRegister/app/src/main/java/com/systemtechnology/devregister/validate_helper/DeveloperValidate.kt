package com.systemtechnology.devregister.validate_helper

import com.systemtechnology.devregister.R
import com.systemtechnology.devregister.class_methods_infix.existsNumber
import com.systemtechnology.devregister.entity.DeveloperEntity
import com.systemtechnology.devregister.mask_helper.CpfCnpjMask
import com.systemtechnology.devregister.utils.UtilsValidate
import com.systemtechnology.devregister.validate_helper.validate_cpf_cnpj.CpfCnpjValidate
import java.util.regex.Pattern

object DeveloperValidate {

    fun isValid(developerEntity : DeveloperEntity) : Int {
        if( developerEntity.name.length < 3 ||
            developerEntity.name.existsNumber() ) {
            return R.string.form_client_error_name
        }

        if( Pattern.compile("[0-9]").matcher( developerEntity.name ).find() ) {
            //if there are numbers in name
            return R.string.form_client_error_name
        }

        if( developerEntity.CPF.length != 11 || !CpfCnpjValidate.isValidCPF( developerEntity.CPF ) ) {
            return R.string.form_client_error_cpf
        }

        if( !UtilsValidate.isValidEmail( developerEntity.email ) ) {
            return R.string.form_client_error_email
        }

        if( developerEntity.password.length < 3 ) {
            return R.string.form_client_error_password_size
        }

        if( !developerEntity.addressIsInitialized() ) {
            return R.string.form_client_error_address
        }

        return 0
    }

}
