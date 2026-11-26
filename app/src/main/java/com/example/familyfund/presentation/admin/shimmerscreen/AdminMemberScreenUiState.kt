package com.example.familyfund.presentation.admin.shimmerscreen

import com.example.familyfund.data.model.MemberModel
import com.example.familyfund.data.model.PaymentRequestModel

sealed interface AdminMemberScreenUiState {


    data object Loading : AdminMemberScreenUiState

    data class Success(

        val member: List<MemberModel>,
        val monthlyPayment: List<PaymentRequestModel>

    ) : AdminMemberScreenUiState


    data class Error(
        val message: String
    ) : AdminMemberScreenUiState

}