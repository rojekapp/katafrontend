package com.paimon.katahack.modelOngkir

import com.google.gson.annotations.SerializedName

data class OngkirRequest (
    @SerializedName("berat")
    var berat: String,
    @SerializedName("alamat_tujuan")
    var tujuan: String,
    @SerializedName("alamat_asal")
    var asal: String
)