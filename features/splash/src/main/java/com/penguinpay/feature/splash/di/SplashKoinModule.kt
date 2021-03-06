package com.penguinpay.feature.splash.di

import com.penguinpay.di.KoinModule
import com.penguinpay.domain.user.GetUserPreferencesUseCase
import com.penguinpay.feature.splash.Navigation
import com.penguinpay.feature.splash.gateway.SplashUserPreferencesGateway
import com.penguinpay.feature.splash.ui.SplashViewModel
import com.penguinpay.navigation.SplashNavigation
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

class SplashKoinModule: KoinModule {
    override val module: Module = module {
        single<SplashNavigation> { Navigation() }

        viewModel {
            SplashViewModel(
                coroutineService = get(),
                getUserPreferencesUseCase = GetUserPreferencesUseCase(SplashUserPreferencesGateway(get(), get()))
            )
        }
    }
}