package com.penguinpay.domain.transfer.entity

data class TransferReceiptEntity(
    val recipientName: String,
    val recipientPhone: String,
    val amount: Double,
    val currency: String
)