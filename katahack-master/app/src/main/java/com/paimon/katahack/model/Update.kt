package com.paimon.katahack.model


import com.google.gson.annotations.SerializedName

data class Update(
    val alamat: String,
    @SerializedName("created_at")
    val createdAt: String,
    val harga: String,
    val id: Int,
    val nama: String,
    val nohp: String,
    val noresi: String,
    val order: String,
    val pembayaran: String,
    val status: String,
    @SerializedName("updated_at")
    val updatedAt: String
)