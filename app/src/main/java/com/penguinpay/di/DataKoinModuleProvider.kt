package com.penguinpay.di

import com.penguinpay.data.remote.exchange.di.RemoteExchangeKoinModule

internal object DataKoinModuleProvider : KoinModuleProvider {
    override fun get(): List<KoinModule> = listOf(
        RemoteExchangeKoinModule()
    )
}