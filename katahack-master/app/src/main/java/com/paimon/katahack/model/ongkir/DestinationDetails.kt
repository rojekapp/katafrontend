package com.paimon.katahack.model.ongkir


import com.google.gson.annotations.SerializedName

data class DestinationDetails(
    @SerializedName("city_id")
    val cityId: String,
    @SerializedName("city_name")
    val cityName: String,
    @SerializedName("postal_code")
    val postalCode: String,
    val province: String,
    @SerializedName("province_id")
    val provinceId: String,
    val type: String
)