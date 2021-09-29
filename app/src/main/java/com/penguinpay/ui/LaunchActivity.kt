package com.penguinpay.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.penguinpay.R
import com.penguinpay.navigation.HomeNavigation
import org.koin.android.ext.android.inject

internal class LaunchActivity : AppCompatActivity() {

    private val homeNavigation: HomeNavigation by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        startActivity(homeNavigation.getHomeIntent(this))
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        finish()
    }
}