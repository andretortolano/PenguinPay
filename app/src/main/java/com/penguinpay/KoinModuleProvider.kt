package com.penguinpay

import com.penguinpay.data.remote.exchange.di.RemoteExchangeKoinModule
import com.penguinpay.di.KoinModule
import com.penguinpay.feature.binaria.di.BinariaKoinModule
import com.penguinpay.feature.home.di.HomeKoinModule
import org.koin.core.module.Module

object KoinModuleProvider {
    private val featureModuleList = arrayListOf(
        BinariaKoinModule(),
        HomeKoinModule()
    )

    private val dataModuleList = arrayListOf<KoinModule>(
        RemoteExchangeKoinModule()
    )

    fun get(): List<Module> =
        featureModuleList.getModule() +
                dataModuleList.getModule()

    private fun ArrayList<KoinModule>.getModule(): List<Module> {
        return map { it.module }
    }
}
