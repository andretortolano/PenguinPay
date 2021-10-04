package com.penguinpay.domain.user.gateway

import com.penguinpay.domain.user.entity.UserThemeStyle

interface UserThemeStyleGateway {
    suspend fun setUserThemeStyle(themeStyle: UserThemeStyle)
}