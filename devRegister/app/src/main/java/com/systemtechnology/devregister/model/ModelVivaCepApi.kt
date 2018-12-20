package com.systemtechnology.devregister.model

import com.systemtechnology.devregister.define_rules.RulesBaseModel
import com.systemtechnology.devregister.entity.Address
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.http.GET

class ModelVivaCepApi(private val listener: VivaCepListener) : RulesBaseModel() {


    fun startSearch(cep: String) : Disposable {

        val path = "https://viacep.com.br/ws/$cep/"

        val observable = getRetrofit( path )
            .create( VivaCepApiRetrofit::class.java )
            .getAddressByVivaCepApi()


        return observable
            .observeOn( AndroidSchedulers.mainThread() )
            .subscribeOn( Schedulers.io() )
            .map {
                if( it.erro ) {
                    null
                } else {
                    it.toAddress()
                }
            }.subscribe( {
                if( it == null ) {
                    listener.whenErrorSearchAddressByCep()
                } else {
                    listener.whenSearchAddressByCepCompleted( it )
                }
            } ) {
                listener.whenErrorSearchAddressByCep()
            }

    }

}

class VivaCepEntity {

    var logradouro  : String? = null
    var complemento : String? = null
    var bairro      : String? = null
    var localidade  : String? = null
    var uf          : String? = null
    var cep         : String? = null

    var erro = false

    fun toAddress() : Address {
        val ad  = Address()
        ad.city             = localidade!!
        ad.neighborhood     = bairro!!
        ad.state            = uf!!
        ad.street           = logradouro!!
        ad.CEP              = cep!!.replace("-", "")
        return ad
    }
}


interface VivaCepListener {
    fun whenSearchAddressByCepCompleted(address: Address)
    fun whenErrorSearchAddressByCep()
}


interface VivaCepApiRetrofit {

    @GET("json")
    fun getAddressByVivaCepApi() : Observable<VivaCepEntity>
}

