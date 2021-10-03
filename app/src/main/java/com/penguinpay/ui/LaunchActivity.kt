package com.penguinpay.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.penguinpay.R
import com.penguinpay.navigation.SplashNavigation
import org.koin.android.ext.android.inject

internal class LaunchActivity : AppCompatActivity() {

    private val splashNavigation: SplashNavigation by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(splashNavigation.getSplashIntent(this))
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        finish()
    }
}