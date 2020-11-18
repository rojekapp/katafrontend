package com.paimon.katahack.model

data class Transaction(
    var date: String,
    var type: String,
    var name: String,
    var number: String,
    var amount: String,
    var account: String,
    var status: String
)
