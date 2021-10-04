package com.penguinpay.domain.user

import com.penguinpay.domain.user.entity.UserThemeStyle
import com.penguinpay.domain.user.gateway.UserThemeStyleGateway

class SetUserThemeStyleUseCase(private val userThemeStyleGateway: UserThemeStyleGateway) {

    data class SetUserThemeStyleRequest(
        val themeStyle: UserThemeStyle
    )

    suspend operator fun invoke(request: SetUserThemeStyleRequest) {
        return userThemeStyleGateway.setUserThemeStyle(request.themeStyle)
    }
}