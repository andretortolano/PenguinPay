package com.penguinpay.feature.binaria.ui.recipientinfo.validator

internal sealed class FormFieldState<T> {

    class Prune<T> : FormFieldState<T>()

    class Invalid<T> : FormFieldState<T>()

    data class Valid<T>(val value: T) : FormFieldState<T>()
}