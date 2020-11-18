package com.paimon.katahack.invoiceAPI

import com.google.gson.annotations.SerializedName

data class InvoiceRequest (
    @SerializedName("text")
    var text: String
)