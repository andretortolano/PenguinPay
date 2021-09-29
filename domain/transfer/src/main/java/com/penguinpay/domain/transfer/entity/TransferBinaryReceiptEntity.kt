package com.penguinpay.domain.transfer.entity

data class TransferBinaryReceiptEntity(
    val recipientName: String,
    val recipientPhone: String,
    val amount: String,
    val currency: String
)