package com.penguinpay.navigation

import android.content.Context
import android.content.Intent

interface BinariaNavigation {
    fun getBinariaIntent(context: Context): Intent
}