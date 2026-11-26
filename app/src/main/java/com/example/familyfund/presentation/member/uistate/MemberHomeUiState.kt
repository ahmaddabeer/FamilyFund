package com.example.familyfund.presentation.member.uistate

import com.example.familyfund.data.model.MemberModel
import com.example.familyfund.data.model.PaymentRequestModel
import java.lang.reflect.Member


sealed interface MemberHomeUiState{




    data object Loading: MemberHomeUiState

    data class Sucess(
        val member: MemberModel?,
        val paymentList:List<PaymentRequestModel>

    ): MemberHomeUiState


    data class Error(

        val message:String
    ): MemberHomeUiState



}