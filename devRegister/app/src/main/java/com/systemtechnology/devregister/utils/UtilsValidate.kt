package com.systemtechnology.devregister.utils


object UtilsValidate {

    @JvmStatic
    fun isValidEmail(target: CharSequence) : Boolean {
        return target.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

}