package com.paimon.katahack.view

import com.paimon.katahack.model.Invoice

interface InvoiceView : InvoiceCallback<Invoice> {
    fun onShowLoading()
}