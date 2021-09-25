package com.penguinpay.di

import org.koin.core.KoinApplication
import org.koin.core.logger.Level
import org.koin.core.module.Module

fun KoinApplication.loadProvidedKoinModules(provider: KoinModuleProvider): KoinApplication {
    if (koin.logger.isAt(Level.INFO)) {
        koin.logger.info("[init] declare Provided Module")
    }

    koin.loadModules(provider.get().mapToModuleList())

    return this
}

fun KoinApplication.loadProvidedKoinModules(providerList: List<KoinModuleProvider>): KoinApplication {
    if (koin.logger.isAt(Level.INFO)) {
        koin.logger.info("[init] declare Provided Modules")
    }

    koin.loadModules(providerList.flatMapToModuleList())

    return this
}

private fun List<KoinModuleProvider>.flatMapToModuleList(): List<Module> {
    return flatMap { it.get().mapToModuleList() }
}

private fun List<KoinModule>.mapToModuleList(): List<Module> {
    return map { it.module }
}