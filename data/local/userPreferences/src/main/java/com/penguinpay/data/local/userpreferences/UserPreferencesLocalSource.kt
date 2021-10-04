package com.penguinpay.data.local.userpreferences

interface UserPreferencesLocalSource {
    suspend fun setThemeStyle(style: ThemeStyleDTO)

    suspend fun getThemeStyle(): ThemeStyleDTO
}