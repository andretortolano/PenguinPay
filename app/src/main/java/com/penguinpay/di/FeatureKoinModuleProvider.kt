package com.penguinpay.di

import com.penguinpay.feature.binaria.di.BinariaKoinModule
import com.penguinpay.feature.home.di.HomeKoinModule
import com.penguinpay.feature.splash.di.SplashKoinModule

internal object FeatureKoinModuleProvider : KoinModuleProvider {
    override fun get(): List<KoinModule> = listOf(
        SplashKoinModule(),
        BinariaKoinModule(),
        HomeKoinModule()
    )
}