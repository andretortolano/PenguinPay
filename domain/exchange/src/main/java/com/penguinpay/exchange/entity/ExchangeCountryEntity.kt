package com.penguinpay.exchange.entity

data class ExchangeCountryEntity(
    val country: String,
    val currency: String,
    val phonePrefix: String,
    val phoneValidSize: Int,
)