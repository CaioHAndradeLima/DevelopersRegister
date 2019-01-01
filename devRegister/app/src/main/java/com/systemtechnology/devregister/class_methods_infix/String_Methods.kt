package com.systemtechnology.devregister.class_methods_infix


 fun String?.existsNumber() : Boolean {

    if (this == null || isEmpty() ) return false

    val sb = StringBuilder()
    var found = false

    for (c in toCharArray() ) {
        if (Character.isDigit(c)) {
            sb.append(c)
            found = true
        } else if (found) {
            return true
        }
    }

    return false
}

