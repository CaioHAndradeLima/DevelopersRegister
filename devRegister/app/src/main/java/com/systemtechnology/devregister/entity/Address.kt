package com.systemtechnology.devregister.entity

import com.orm.SugarRecord

class Address : SugarRecord() {
    //cep , logradouro, bairro , número, estado

    lateinit var CEP    : String
    lateinit var street : String
    lateinit var neighborhood : String
    lateinit var city         : String
    lateinit var state        : String

    var complementary: String = ""
    var houseNumber  : String = ""



    companion object {
        fun getAllAddress() : MutableList<Address>? {
            return listAll( Address::class.java  )
        }
    }

}