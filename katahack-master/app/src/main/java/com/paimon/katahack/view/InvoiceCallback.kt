package com.paimon.katahack.view

interface InvoiceCallback<T> {
    fun onGetInvoice(data: T)
    fun onGetInvoiceError(message: String)
}
