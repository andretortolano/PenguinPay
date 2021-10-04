package com.penguinpay.feature.splash.gateway

import com.penguinpay.data.local.userpreferences.ThemeStyleDTO
import com.penguinpay.data.local.userpreferences.UserPreferencesLocalSource
import com.penguinpay.domain.user.entity.UserPreferencesEntity
import com.penguinpay.domain.user.entity.UserThemeStyle
import com.penguinpay.domain.user.gateway.UserPreferencesGateway
import com.penguinpay.libraries.coroutines.android.CoroutineService
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class SplashUserPreferencesGateway(
    private val coroutineService: CoroutineService,
    private val localSource: UserPreferencesLocalSource
) : UserPreferencesGateway {

    override suspend fun getUserPreferences(): UserPreferencesEntity = withContext(coroutineService.IO) {
        delay(2000)
        return@withContext localSource.getThemeStyle().toUserPreferencesEntity()
    }

    private fun ThemeStyleDTO.toUserPreferencesEntity(): UserPreferencesEntity {
        val userThemeStyle = when (this) {
            ThemeStyleDTO.ALWAYS_DARK -> UserThemeStyle.AlwaysDark
            ThemeStyleDTO.ALWAYS_LIGHT -> UserThemeStyle.AlwaysLight
            ThemeStyleDTO.ANDROID -> UserThemeStyle.Android
        }
        return UserPreferencesEntity(themeStyle = userThemeStyle)
    }
}
