package com.systemtechnology.devregister

import com.systemtechnology.devregister.validate_helper.validate_cpf_cnpj.CpfCnpjValidate
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test

class CpfCnpjValidateUnitTest {

    @Test
    fun testValidationCpf() {
        val cpf1 = "2424knsid"
        val cpf2 = "2324242442"
        val cpf3 = "1111111111"
        val cpf4 = "44574551801"

        Assert.assertFalse( CpfCnpjValidate.isValidCPF( cpf1 ) )
        Assert.assertFalse( CpfCnpjValidate.isValidCPF( cpf2 ) )
        Assert.assertFalse( CpfCnpjValidate.isValidCPF( cpf3 ) )
        Assert.assertTrue(  CpfCnpjValidate.isValidCPF( cpf4 ) )
    }

}