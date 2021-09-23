package com.penguinpay.domain.exchange.interactor

import com.penguinpay.domain.exchange.exception.ExchangeRateException
import com.penguinpay.domain.exchange.gateway.ExchangeRateGateway
import com.penguinpay.lib.coroutines.CoroutineService
import kotlinx.coroutines.withContext

class ExchangeUSDUseCase(private val coroutines: CoroutineService, private val exchangeRateGateway: ExchangeRateGateway) {

    data class Request(
        val currency: String,
        val amount: Double,
    )

    sealed class Result {
        data class Success(val amount: Double) : Result()
        object SomethingWentWrong : Result()
    }

    suspend operator fun invoke(request: Request): Result = withContext(coroutines.IO) {
        return@withContext try {
            exchangeRateGateway.getExchangeRate("USD", request.currency).let {
                Result.Success(request.amount * it)
            }
        } catch (e: ExchangeRateException) {
            Result.SomethingWentWrong
        }
    }
}