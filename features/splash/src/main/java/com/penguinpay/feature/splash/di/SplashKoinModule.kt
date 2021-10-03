package com.penguinpay.feature.splash.di

import com.penguinpay.di.KoinModule
import com.penguinpay.feature.splash.Navigation
import com.penguinpay.navigation.SplashNavigation
import org.koin.core.module.Module
import org.koin.dsl.module

class SplashKoinModule: KoinModule {
    override val module: Module = module {
        single<SplashNavigation> { Navigation() }
    }
}