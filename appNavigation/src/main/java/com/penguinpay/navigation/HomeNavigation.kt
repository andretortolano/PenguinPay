package com.penguinpay.navigation

import android.content.Context
import android.content.Intent

interface HomeNavigation {
    fun getHomeIntent(context: Context): Intent
}