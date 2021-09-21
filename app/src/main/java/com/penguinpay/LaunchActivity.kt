package com.penguinpay

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.penguinpay.navigation.HomeNavigation
import org.koin.android.ext.android.inject

class LaunchActivity : AppCompatActivity() {

    private val homeNavigation: HomeNavigation by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity(homeNavigation.getHomeIntent(this))
    }
}