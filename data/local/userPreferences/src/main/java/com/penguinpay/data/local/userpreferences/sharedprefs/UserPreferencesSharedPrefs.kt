package com.penguinpay.data.local.userpreferences.sharedprefs

import android.content.Context
import com.andretortolano.sharedprefs.SharedPrefs
import com.penguinpay.data.local.userpreferences.ThemeStyleDTO

internal class UserPreferencesSharedPrefs(context: Context) : SharedPrefs(context, "user_preferences") {

    var themeStyle by stringPref("theme_style", ThemeStyleDTO.ANDROID.name)
}