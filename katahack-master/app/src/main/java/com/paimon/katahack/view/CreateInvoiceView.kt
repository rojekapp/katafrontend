package com.paimon.katahack.view

import com.paimon.katahack.model.CreateInvoice

interface CreateInvoiceView : CreateInvoiceCallback<CreateInvoice> {
    fun onShowLoading()
}