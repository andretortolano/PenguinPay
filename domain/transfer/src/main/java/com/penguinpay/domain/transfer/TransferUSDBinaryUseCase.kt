package com.penguinpay.domain.transfer

import com.penguinpay.domain.transfer.TransferUSDUseCase.TransferUSDRequest
import com.penguinpay.domain.transfer.entity.TransferBinaryReceiptEntity
import com.penguinpay.libraries.extensions.binary.asBinaryToDouble
import com.penguinpay.libraries.extensions.binary.toBinaryString

class TransferUSDBinaryUseCase(
    private val transferUSDUseCase: TransferUSDUseCase
) {

    data class TransferUSDBinaryRequest(
        val recipientName: String,
        val recipientPhone: String,
        val usdBinaryAmount: String,
        val currency: String,
    )

    data class TransferUSDBinaryResponse(
        val receipt: TransferBinaryReceiptEntity
    )

    suspend operator fun invoke(request: TransferUSDBinaryRequest): TransferUSDBinaryResponse {
        return with(request) {
            transferUSDUseCase(
                TransferUSDRequest(
                    recipientName = recipientName,
                    recipientPhone = recipientPhone,
                    usdAmount = usdBinaryAmount.asBinaryToDouble(),
                    currency = currency,
                )
            ).let {
                TransferUSDBinaryResponse(
                    TransferBinaryReceiptEntity(
                        recipientName = recipientName,
                        recipientPhone = recipientPhone,
                        amount = it.receipt.amount.toBinaryString(),
                        currency = currency,
                    )
                )
            }
        }
    }
}