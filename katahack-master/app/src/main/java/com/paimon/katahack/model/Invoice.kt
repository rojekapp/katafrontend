package com.paimon.katahack.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

data class Invoice(
    val `data`: List<InvoiceData>
)

@Parcelize
data class InvoiceData(
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
) : Parcelable
