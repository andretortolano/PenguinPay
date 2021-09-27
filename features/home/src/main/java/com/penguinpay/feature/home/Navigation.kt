package com.penguinpay.feature.home

import android.content.Context
import android.content.Intent
import com.penguinpay.navigation.HomeNavigation

internal class Navigation : HomeNavigation {
    override fun getHomeIntent(context: Context): Intent {
        return HomeActivity.getIntent(context)
    }
}