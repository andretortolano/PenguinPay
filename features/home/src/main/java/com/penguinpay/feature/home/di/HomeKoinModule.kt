package com.penguinpay.feature.home.di

import com.penguinpay.di.KoinModule
import com.penguinpay.feature.home.Navigation
import com.penguinpay.navigation.HomeNavigation
import org.koin.core.module.Module
import org.koin.dsl.module

class HomeKoinModule : KoinModule {
    override val module: Module = module {
        single<HomeNavigation> { Navigation() }
    }
}