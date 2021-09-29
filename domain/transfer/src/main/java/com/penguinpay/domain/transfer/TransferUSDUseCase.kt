package com.penguinpay.domain.transfer

import com.penguinpay.domain.exchange.ExchangeUSDUseCase
import com.penguinpay.domain.exchange.ExchangeUSDUseCase.ExchangeUSDRequest
import com.penguinpay.domain.transfer.entity.TransferReceiptEntity
import com.penguinpay.domain.transfer.gateway.TransferGateway

class TransferUSDUseCase(
    private val transferGateway: TransferGateway,
    private val exchangeUSDUseCase: ExchangeUSDUseCase,
) {

    data class TransferUSDRequest(
        val recipientName: String,
        val recipientPhone: String,
        val usdAmount: Double,
        val currency: String,
    )

    data class TransferUSDResponse(
        val receipt: TransferReceiptEntity
    )

    /**
     * This is just a Mock data for the sample
     */
    suspend operator fun invoke(request: TransferUSDRequest): TransferUSDResponse {
        return with(request) {
            exchangeUSDUseCase(ExchangeUSDRequest(currency, usdAmount)).let { exchangeResult ->
                transferGateway.transfer(recipientName, recipientPhone, currency, exchangeResult.exchangedAmount)

                TransferUSDResponse(
                    TransferReceiptEntity(
                        recipientName = request.recipientName,
                        recipientPhone = request.recipientPhone,
                        amount = exchangeResult.exchangedAmount,
                        currency = request.currency,
                    )
                )
            }
        }
    }
}