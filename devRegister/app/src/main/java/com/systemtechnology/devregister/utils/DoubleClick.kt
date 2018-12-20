package com.systemtechnology.devregister.utils

class DoubleClick {

    private var lastClick : Long = 0

    fun isSingleClick(): Boolean {
        val now = System.currentTimeMillis()
        val result = now - lastClick > 700L
        lastClick = now
        return result
    }

    fun isDoubleClick() : Boolean {
        return !isSingleClick()
    }

}
