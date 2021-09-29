package com.penguinpay.feature.binaria.ui.send

import com.penguinpay.domain.exchange.ExchangeUSDBinaryUseCase
import com.penguinpay.domain.transfer.TransferUSDBinaryUseCase
import com.penguinpay.domain.transfer.TransferUSDBinaryUseCase.TransferUSDBinaryRequest
import com.penguinpay.domain.transfer.entity.TransferBinaryReceiptEntity
import com.penguinpay.libraries.coroutines.CoroutineService
import kotlinx.coroutines.withContext

internal class SendModel(
    private val coroutineService: CoroutineService,
    private val transferUSDBinaryUseCase: TransferUSDBinaryUseCase,
    private val exchangeUSDBinaryUseCase: ExchangeUSDBinaryUseCase
) {

    suspend fun transferUSDBinary(
        recipientName: String,
        recipientPhone: String,
        usdBinaryAmount: String,
        currency: String
    ): TransferBinaryReceiptEntity = withContext(coroutineService.IO) {
        return@withContext transferUSDBinaryUseCase(
            TransferUSDBinaryRequest(
                recipientName = recipientName,
                recipientPhone = recipientPhone,
                usdBinaryAmount = usdBinaryAmount,
                currency = currency,
            )
        ).receipt
    }

    suspend fun getExchangedUSDBinaryAmount(newCurrency: String, usdBinaryValue: String): String = withContext(coroutineService.IO) {
        return@withContext exchangeUSDBinaryUseCase(
            ExchangeUSDBinaryUseCase.ExchangeUSDBinaryRequest(
                newCurrency,
                usdBinaryValue
            )
        ).exchangedBinaryAmount
    }
}