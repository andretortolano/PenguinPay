package com.penguinpay.domain.user.entity

sealed class UserThemeStyle {
    object AlwaysLight: UserThemeStyle()
    object AlwaysDark: UserThemeStyle()
    object Android: UserThemeStyle()
}