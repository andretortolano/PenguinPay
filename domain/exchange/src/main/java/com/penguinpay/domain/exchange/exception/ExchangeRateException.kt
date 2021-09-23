package com.penguinpay.domain.exchange.exception

class ExchangeRateException(
    fromCurrency: String,
    toCurrency: String
) : Exception("Something went wrong while trying to get ExchangeRate fromCurrency $fromCurrency toCurrency $toCurrency")