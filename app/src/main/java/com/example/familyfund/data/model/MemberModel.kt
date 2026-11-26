package com.example.familyfund.data.model

data class MemberModel(

    val memberId:String = "",
    val name:String = "",
    val monthlyFees:Int = 250,
    val phone: String = "",
    val isActive:Boolean = true,
    val createdAt:Long = 0L,
    val updatedAt:Long = 0L,

    )
