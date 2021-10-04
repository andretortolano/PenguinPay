package com.penguinpay.data.local.userpreferences.sharedprefs

import com.penguinpay.data.local.userpreferences.ThemeStyleDTO
import com.penguinpay.data.local.userpreferences.UserPreferencesLocalSource

internal class SharedPrefsUserPreferencesLocalSource(
    private val userPreferencesSharedPrefs: UserPreferencesSharedPrefs
) : UserPreferencesLocalSource {

    override suspend fun setThemeStyle(style: ThemeStyleDTO) {
        userPreferencesSharedPrefs.themeStyle = style.name
    }

    override suspend fun getThemeStyle(): ThemeStyleDTO {
        return ThemeStyleDTO.valueOf(userPreferencesSharedPrefs.themeStyle)
    }
}