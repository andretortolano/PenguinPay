package com.penguinpay.di

internal object AppKoinModuleProvider : KoinModuleProvider {
    override fun get(): List<KoinModule> = listOf(
        PenguinPayKoinModule()
    )
}