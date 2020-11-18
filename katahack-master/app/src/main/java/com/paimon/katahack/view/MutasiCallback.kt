package com.paimon.katahack.view

interface MutasiCallback<T> {
    fun onGetMutasi(data: T)
    fun onGetMutasiError(message: String)
}
