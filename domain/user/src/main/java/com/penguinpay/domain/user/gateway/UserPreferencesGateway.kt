package com.penguinpay.domain.user.gateway

import com.penguinpay.domain.user.entity.UserPreferencesEntity

interface UserPreferencesGateway {
    suspend fun getUserPreferences(): UserPreferencesEntity
}