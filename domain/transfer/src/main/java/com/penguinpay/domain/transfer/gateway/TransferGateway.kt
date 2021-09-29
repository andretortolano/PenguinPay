package com.penguinpay.domain.transfer.gateway

interface TransferGateway {

    suspend fun transfer(recipientName: String, recipientPhone: String, recipientCurrency: String, recipientAmount: Double)
}