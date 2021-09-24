package com.penguinpay.domain.exchange.entity

data class ExchangeCountryEntity(
    val countryName: String,
    val countryAcronym: String,
    val phonePrefix: String,
    val phoneValidSize: Int,
)