package com.penguinpay.data.local.userpreferences.di

import com.penguinpay.data.local.userpreferences.UserPreferencesLocalSource
import com.penguinpay.data.local.userpreferences.sharedprefs.SharedPrefsUserPreferencesLocalSource
import com.penguinpay.data.local.userpreferences.sharedprefs.UserPreferencesSharedPrefs
import com.penguinpay.di.KoinModule
import org.koin.core.module.Module
import org.koin.dsl.module

class LocalUserPreferencesKoinModule : KoinModule {
    override val module: Module = module {
        single<UserPreferencesLocalSource> { SharedPrefsUserPreferencesLocalSource(
            userPreferencesSharedPrefs = UserPreferencesSharedPrefs(get())
        ) }
    }
}