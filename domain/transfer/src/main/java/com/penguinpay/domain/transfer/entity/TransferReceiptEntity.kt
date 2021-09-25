package com.penguinpay.domain.transfer.entity

data class TransferReceiptEntity(
    val recipientName: String,
    val recipientPhone: String,
    val recipientCountryName: String,
    val sentUSDBinary: String,
    val receivedBinary: String,
    val transactionDate: String,
)