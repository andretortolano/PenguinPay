package com.penguinpay.domain.exchange.entity

data class ExchangeCountryEntity(
    val country: String,
    val currency: String,
    val phonePrefix: String,
    val phoneValidSize: Int,
)