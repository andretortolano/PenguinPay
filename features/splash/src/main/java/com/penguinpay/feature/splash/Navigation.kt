package com.penguinpay.feature.splash

import android.content.Context
import android.content.Intent
import com.penguinpay.feature.splash.ui.SplashActivity
import com.penguinpay.navigation.SplashNavigation

internal class Navigation: SplashNavigation {
    override fun getSplashIntent(context: Context): Intent {
        return SplashActivity.getIntent(context)
    }
}