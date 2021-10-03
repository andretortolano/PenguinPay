package com.penguinpay.navigation

import android.content.Context
import android.content.Intent

interface SplashNavigation {
    fun getSplashIntent(context: Context): Intent
}