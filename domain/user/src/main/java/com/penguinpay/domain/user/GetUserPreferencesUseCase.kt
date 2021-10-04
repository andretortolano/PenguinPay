package com.penguinpay.domain.user

import com.penguinpay.domain.user.entity.UserPreferencesEntity
import com.penguinpay.domain.user.gateway.UserPreferencesGateway

class GetUserPreferencesUseCase(private val userPreferencesGateway: UserPreferencesGateway) {

    suspend operator fun invoke(): UserPreferencesEntity {
        return userPreferencesGateway.getUserPreferences()
    }
}