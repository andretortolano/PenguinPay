package com.penguinpay.feature.binaria.gateway

import com.penguinpay.domain.transfer.gateway.TransferGateway
import kotlinx.coroutines.delay

internal class TransferGatewayImpl : TransferGateway {

    override suspend fun transfer(recipientName: String, recipientPhone: String, recipientCurrency: String, recipientAmount: Double) {
        delay(2000)
        // TODO perform some task
    }
}
