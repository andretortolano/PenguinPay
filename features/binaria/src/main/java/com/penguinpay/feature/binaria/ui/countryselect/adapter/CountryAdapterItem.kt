package com.penguinpay.feature.binaria.ui.countryselect.adapter

import com.penguinpay.domain.exchange.entity.ExchangeCountryEntity

sealed class CountryAdapterItem {
    data class CountryItem(val country: ExchangeCountryEntity) : CountryAdapterItem()
}