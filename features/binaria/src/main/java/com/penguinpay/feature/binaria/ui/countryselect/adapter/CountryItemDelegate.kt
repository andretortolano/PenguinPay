package com.penguinpay.feature.binaria.ui.countryselect.adapter

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.penguinpay.domain.exchange.entity.ExchangeCountryEntity
import com.penguinpay.feature.binaria.R
import com.penguinpay.feature.binaria.databinding.ItemCountryAdapterBinding
import com.penguinpay.feature.binaria.ui.countryselect.adapter.CountryAdapterItem.CountryItem

internal object CountryItemDelegate {
    fun get(onClick: (country: ExchangeCountryEntity) -> Unit) =
        adapterDelegateViewBinding<CountryItem, CountryAdapterItem, ItemCountryAdapterBinding>(
            { layoutInflater, root -> ItemCountryAdapterBinding.inflate(layoutInflater, root, false) }
        ) {
            with(binding) {
                root.setOnClickListener { onClick(item.country) }
            }
            bind {
                with(binding) {
                    countryName.text =
                        getString(R.string.country_item_adapter_description, item.country.countryAcronym, item.country.countryName)
                }
            }
        }
}