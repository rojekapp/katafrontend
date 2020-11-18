package com.paimon.katahack.view

interface CreateInvoiceCallback<T> {
    fun onSetInvoice(data: T)
    fun onSetInvoiceError(message: String)
}
