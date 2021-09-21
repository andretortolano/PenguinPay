package com.penguinpay.feature.home

import com.penguinpay.common.FeatureModule
import com.penguinpay.navigation.HomeNavigation
import org.koin.core.module.Module
import org.koin.dsl.module

class HomeFeatureModule : FeatureModule {
    override val module: Module = module {
        single<HomeNavigation> { Navigation() }
    }
}