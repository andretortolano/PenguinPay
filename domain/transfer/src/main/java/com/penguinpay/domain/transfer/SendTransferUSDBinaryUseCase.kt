package com.penguinpay.domain.transfer

import com.penguinpay.domain.transfer.entity.TransferReceiptEntity
import com.penguinpay.libraries.coroutines.CoroutineService
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class SendTransferUSDBinaryUseCase(private val coroutines: CoroutineService) {

    data class Request(
        val recipientName: String,
        val recipientPhone: String,
        val sentUSDBinary: String,
        val currency: String,
        val receivedBinary: String,
    )

    suspend operator fun invoke(request: Request): TransferReceiptEntity = withContext(coroutines.IO) {
        delay(2000)
        return@withContext TransferReceiptEntity(
            recipientName = request.recipientName,
            recipientPhone = request.recipientPhone,
            currency = request.currency,
            valueBinary = request.receivedBinary,
        )
    }
}