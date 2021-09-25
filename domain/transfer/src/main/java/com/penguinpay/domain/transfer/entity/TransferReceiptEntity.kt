package com.penguinpay.domain.transfer.entity

data class TransferReceiptEntity(
    val recipientName: String,
    val recipientPhone: String,
    val valueBinary: String,
    val currency: String
)