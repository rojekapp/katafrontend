package com.paimon.katahack.invoiceAPI

import com.google.gson.annotations.SerializedName

data class InvoiceResponse (
    @SerializedName("nama")
    var nama: String,

    @SerializedName("lokasi")
    var lokasi: String,

    @SerializedName("phone")
    var phone: String,

    @SerializedName("harga")
    var harga: Int
)