package com.penguinpay

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class PenguinPayApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initLogger()
        initDependencyInjection()
    }

    private fun initLogger() {
        // TODO
    }

    private fun initDependencyInjection() {
        startKoin {
            androidContext(applicationContext)

            loadKoinModules(FeatureModuleProvider.get())
        }
    }
}