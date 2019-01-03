package com.systemtechnology.devregister

import com.systemtechnology.devregister.model.VivaCepApiRetrofit
import org.junit.Assert
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ConsistencyAddressTest {

    @Test
    fun testConsistencyJson() {

        val cep = "07183070"
        val path = "https://viacep.com.br/ws/$cep/"

        Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(path)
            .build()
            .create( VivaCepApiRetrofit::class.java )
            .getAddressByVivaCepApi()
            .subscribe({

                Assert.assertFalse( it.erro )

                Assert.assertNotNull( it.bairro )
                Assert.assertNotNull( it.cep )
                Assert.assertNotNull( it.localidade )
                Assert.assertNotNull( it.logradouro )
                Assert.assertNotNull( it.uf )
            }) {
                throw IllegalStateException("See if the json is correct, the internet is working or the API server is running")
            }

    }
}
