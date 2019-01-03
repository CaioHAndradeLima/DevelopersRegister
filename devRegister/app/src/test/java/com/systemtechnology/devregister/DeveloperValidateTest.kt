package com.systemtechnology.devregister

import com.systemtechnology.devregister.entity.DeveloperEntity
import com.systemtechnology.devregister.validate_helper.DeveloperValidate
import org.junit.Test

class DeveloperValidateTest {

    @Test
    fun testIfValidateIsWorking1() {
        val developer = DeveloperEntity()

        assert( DeveloperValidate.isValid( developer ) != 0 )
    }

    @Test
    fun testIfValidateIsWorking2() {
        val developer = DeveloperEntity()
        developer.name = "224245235"
        assert( DeveloperValidate.isValid( developer ) != 0 )
    }

    @Test
    fun testIfValidateIsWorking3() {
        val developer = DeveloperEntity()
        developer.name = "cadifo0 kdosaf"
        developer.CPF = "224245235"
        assert( DeveloperValidate.isValid( developer ) != 0 )
    }

    @Test
    fun testIfValidateIsWorking4() {
        val developer = DeveloperEntity()
        developer.name = "cadifo0 kdosaf"
        developer.CPF = "224245235"
        assert( DeveloperValidate.isValid( developer ) != 0 )
    }

    @Test
    fun testIfValidateIsWorking5() {
        val developer = DeveloperEntity()
        developer.name = "Caio Henrique"
        developer.CPF = "44574551801"

        //without address
        assert( DeveloperValidate.isValid( developer ) != 0 )
    }

}
