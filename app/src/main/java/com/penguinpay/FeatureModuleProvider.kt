package com.penguinpay

import com.penguinpay.common.FeatureModule
import com.penguinpay.feature.binaria.BinariaFeatureModule
import com.penguinpay.feature.home.HomeFeatureModule
import org.koin.core.module.Module

object FeatureModuleProvider {
    private val featureModuleList = arrayListOf(
        BinariaFeatureModule(),
        HomeFeatureModule()
    )

    fun get(): List<Module> = featureModuleList.getModule()

    private fun ArrayList<FeatureModule>.getModule(): List<Module> {
        return map { it.module }
    }
}
