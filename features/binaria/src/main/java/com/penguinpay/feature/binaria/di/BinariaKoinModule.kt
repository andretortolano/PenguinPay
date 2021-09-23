package com.penguinpay.feature.binaria.di

import com.penguinpay.di.KoinModule
import com.penguinpay.feature.binaria.Navigation
import com.penguinpay.navigation.BinariaNavigation
import org.koin.core.module.Module
import org.koin.dsl.module

class BinariaKoinModule : KoinModule {
    override val module: Module = module {
        single<BinariaNavigation> { Navigation() }
    }
}