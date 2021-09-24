package com.penguinpay.domain.exchange.interactor

import com.penguinpay.libraries.coroutines.CoroutineService
import com.penguinpay.libraries.extensions.binary.asBinaryToInt
import com.penguinpay.libraries.extensions.binary.toBinaryString
import kotlinx.coroutines.withContext
import kotlin.math.roundToInt

class ExchangeUSDBinaryUseCase(private val coroutines: CoroutineService, private val exchangeUSDUseCase: ExchangeUSDUseCase) {

    data class Request(
        val currency: String,
        val amount: String,
    )

    sealed class Result {
        data class Success(val amount: String) : Result()
        object AmountIsNotBinaryError : Result()
        object SomethingWentWrong : Result()
    }

    suspend operator fun invoke(request: Request): Result = withContext(coroutines.IO) {
        return@withContext try {
            with(exchangeUSDUseCase(ExchangeUSDUseCase.Request(request.currency, request.amount.asBinaryToInt().toDouble()))) {
                when (this) {
                    is ExchangeUSDUseCase.Result.Success -> Result.Success(this.amount.roundToInt().toBinaryString())
                    ExchangeUSDUseCase.Result.SomethingWentWrong -> Result.SomethingWentWrong
                }
            }
        } catch (e: NumberFormatException) {
            Result.AmountIsNotBinaryError
        }
    }
}