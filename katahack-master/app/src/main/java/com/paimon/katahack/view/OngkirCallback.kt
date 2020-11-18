package com.paimon.katahack.view

interface OngkirCallback<T> {
    fun onSetOngkir(data: T)
    fun onSetOngkirError(message: String)
}
