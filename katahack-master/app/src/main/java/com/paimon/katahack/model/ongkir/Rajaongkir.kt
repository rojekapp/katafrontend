package com.paimon.katahack.model.ongkir


import com.google.gson.annotations.SerializedName

data class Rajaongkir(
    @SerializedName("destination_details")
    val destinationDetails: DestinationDetails,
    @SerializedName("origin_details")
    val originDetails: OriginDetails,
    val query: Query,
    val results: List<Result>,
    val status: Status
)