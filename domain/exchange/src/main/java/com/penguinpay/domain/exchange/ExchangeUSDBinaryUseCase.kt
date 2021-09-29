package com.penguinpay.domain.exchange

import com.penguinpay.libraries.extensions.binary.asBinaryToDouble
import com.penguinpay.libraries.extensions.binary.toBinaryString
import java.io.BufferedReader
import java.io.IOException

class ExchangeUSDBinaryUseCase(private val exchangeUSDUseCase: ExchangeUSDUseCase) {

    data class ExchangeUSDBinaryRequest(
        val currency: String,
        val amount: String,
    )

    data class ExchangeUSDBinaryResult(
        val exchangedBinaryAmount: String
    )

    suspend operator fun invoke(request: ExchangeUSDBinaryRequest): ExchangeUSDBinaryResult {
        return ExchangeUSDBinaryResult(
            exchangeUSDUseCase(
                ExchangeUSDUseCase.ExchangeUSDRequest(
                    request.currency,
                    request.amount.asBinaryToDouble(),
                )
            ).exchangedAmount.toBinaryString()
        )
    }
}