package br.systemtechnology.commerce.activities.get_address_by_cep.helper

import android.text.Editable
import android.text.TextWatcher
import android.util.Log

import io.reactivex.Observable

class TextWatcherCepListener(private val leta8c : ListenerWhenEditTextArrived8characters) : TextWatcher {

    override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {  }

    override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) { }

    override fun afterTextChanged(editable: Editable) {
        Observable
                .just(editable.toString())
                .map { it.length }
                .filter { it == 8 }
                .doAfterNext {
                    leta8c.whenEditTextArrived8characters()
                }.subscribe()
    }
}


interface ListenerWhenEditTextArrived8characters {
    fun whenEditTextArrived8characters()
}