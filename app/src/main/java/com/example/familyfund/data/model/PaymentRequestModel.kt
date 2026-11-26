package com.example.familyfund.data.model

data class PaymentRequestModel(

    val memberId:String= "",
    val memberName:String = "",
    val amount:Int = 250,
    val message:String = "",
    val status:String = "Verification in progress.",
    val createdAt:Long = 0L
)