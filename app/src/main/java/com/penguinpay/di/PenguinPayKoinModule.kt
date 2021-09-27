package com.penguinpay.di

import com.penguinpay.coroutines.AppCoroutineService
import com.penguinpay.libraries.coroutines.CoroutineService
import org.koin.core.module.Module
import org.koin.dsl.module

internal class PenguinPayKoinModule : KoinModule {
    override val module: Module = module {
        single<CoroutineService> { AppCoroutineService() }
    }
}