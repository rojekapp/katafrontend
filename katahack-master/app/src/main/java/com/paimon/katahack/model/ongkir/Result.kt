package com.paimon.katahack.model.ongkir


data class Result(
    val code: String,
    val costs: List<Cost>,
    val name: String
)