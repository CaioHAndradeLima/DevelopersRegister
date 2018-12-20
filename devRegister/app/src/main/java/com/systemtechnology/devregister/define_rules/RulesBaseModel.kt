package com.systemtechnology.devregister.define_rules

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

abstract class RulesBaseModel {

    /**
     * create new instance and return the object retrofit
     *
     * @author Caio
     * @version 1
     * @since 1
     *
     */
    internal fun getRetrofit(url: String): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(url)
            .build()
    }

}
