package com.penguinpay

import android.app.Application
import com.penguinpay.di.AppKoinModuleProvider
import com.penguinpay.di.DataKoinModuleProvider
import com.penguinpay.di.FeatureKoinModuleProvider
import com.penguinpay.di.loadProvidedKoinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.logger.PrintLogger

@Suppress("unused")
internal class PenguinPayApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initDependencyInjection()
    }

    private fun initDependencyInjection() {
        startKoin {
            if (BuildConfig.DEBUG) {
                logger(PrintLogger())
            }

            androidContext(applicationContext)

            loadProvidedKoinModules(
                listOf(
                    AppKoinModuleProvider,
                    DataKoinModuleProvider,
                    FeatureKoinModuleProvider
                )
            )
        }
    }
}