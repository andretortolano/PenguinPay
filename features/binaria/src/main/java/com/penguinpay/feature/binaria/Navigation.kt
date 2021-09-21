package com.penguinpay.feature.binaria

import android.content.Context
import android.content.Intent
import com.penguinpay.navigation.BinariaNavigation

internal class Navigation : BinariaNavigation {
    override fun getBinariaIntent(context: Context): Intent {
        return BinariaActivity.getIntent(context)
    }
}