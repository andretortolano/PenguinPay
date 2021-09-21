package com.penguinpay.feature.binaria

import com.penguinpay.common.FeatureModule
import com.penguinpay.navigation.BinariaNavigation
import org.koin.core.module.Module
import org.koin.dsl.module

class BinariaFeatureModule : FeatureModule {
    override val module: Module = module {
        single<BinariaNavigation> { Navigation() }
    }
}