package com.penguinpay.di

interface KoinModuleProvider {
    fun get(): List<KoinModule>
}