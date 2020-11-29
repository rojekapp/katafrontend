package com.paimon.katahack.modelOngkir

data class OngkirResponse(
	val rajaongkir: Rajaongkir? = null
)

data class Rajaongkir(
	val query: Query? = null,
	val destinationDetails: DestinationDetails? = null,
	val originDetails: OriginDetails? = null,
	val results: ArrayList<ResultsItem?>? = null,
	val status: Status? = null
)

data class Status(
	val code: Int? = null,
	val description: String? = null
)

data class ResultsItem(
	val costs: List<CostsItem?>? = null,
	val code: String? = null,
	val name: String? = null
)

data class Query(
	val originType: String? = null,
	val courier: String? = null,
	val origin: String? = null,
	val destination: String? = null,
	val destinationType: String? = null,
	val weight: Int? = null
)

data class DestinationDetails(
	val cityName: String? = null,
	val province: String? = null,
	val provinceId: String? = null,
	val type: String? = null,
	val postalCode: String? = null,
	val cityId: String? = null
)

data class OriginDetails(
	val cityName: String? = null,
	val province: String? = null,
	val provinceId: String? = null,
	val type: String? = null,
	val postalCode: String? = null,
	val cityId: String? = null
)

data class CostsItem(
	val cost: List<CostItem?>? = null,
	val service: String? = null,
	val description: String? = null
)

data class CostItem(
	val note: String? = null,
	val etd: String? = null,
	val value: Int? = null
)

