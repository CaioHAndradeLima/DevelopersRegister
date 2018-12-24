package com.systemtechnology.devregister.utils

import android.text.TextUtils


object UtilsValidate {

    @JvmStatic
    fun isValidEmail(target: CharSequence) : Boolean {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

}