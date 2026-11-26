package com.example.familyfund.presentation.admin

import com.example.familyfund.data.model.MemberModel
import com.example.familyfund.data.model.PaymentRequestModel
import com.example.familyfund.data.repository.MemberRepository

sealed interface AdminHomeScreenUiState {


    data object Loading : AdminHomeScreenUiState

    data class Success(
        val allMonthPaymentRecord: List<PaymentRequestModel>,

        val allPaymentRecordCurrentMonth: List<PaymentRequestModel>,
        val allmember:List<MemberModel>

    ) : AdminHomeScreenUiState

    data class Error(

        val message: String
    ) : AdminHomeScreenUiState
}