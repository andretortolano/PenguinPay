package com.penguinpay.feature.binaria.ui.countryselect.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.penguinpay.domain.exchange.entity.ExchangeCountryEntity

internal class CountryAdapter(private val listener: OnCountryAdapterListener) :
    AsyncListDifferDelegationAdapter<CountryAdapterItem>(CountryAdapterDiffConfig()) {

    interface OnCountryAdapterListener {
        fun onItemCountryClick(country: ExchangeCountryEntity)
    }

    init {
        delegatesManager.addDelegate(CountryItemDelegate.get(listener::onItemCountryClick))
    }

    fun update(list: List<ExchangeCountryEntity>) {
        items = list.map { CountryAdapterItem.CountryItem(it) }
    }

    class CountryAdapterDiffConfig : DiffUtil.ItemCallback<CountryAdapterItem>() {

        override fun areItemsTheSame(oldItem: CountryAdapterItem, newItem: CountryAdapterItem): Boolean {
            if (oldItem is CountryAdapterItem.CountryItem && newItem is CountryAdapterItem.CountryItem) {
                return oldItem.country == newItem.country
            }

            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CountryAdapterItem, newItem: CountryAdapterItem): Boolean {
            return oldItem == newItem
        }
    }
}