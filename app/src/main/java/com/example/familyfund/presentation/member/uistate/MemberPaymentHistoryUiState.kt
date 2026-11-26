package com.example.familyfund.presentation.member.uistate

import com.example.familyfund.data.model.MemberModel
import com.example.familyfund.data.model.PaymentRequestModel

sealed interface MemberPaymentHistoryUiState {


    data object Loading : MemberPaymentHistoryUiState

    data class Success(
        val member: MemberModel?,
        val paymentList: List<PaymentRequestModel>,
        val allPaymentList:List<PaymentRequestModel>
    ) : MemberPaymentHistoryUiState


    data class Error(
        val message: String
    ) : MemberPaymentHistoryUiState
}