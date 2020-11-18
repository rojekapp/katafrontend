package com.paimon.katahack.view

interface UpdateCallback<T> {
    fun onGetUpdate(data: T)
    fun onGetUpdateError(message: String)
}