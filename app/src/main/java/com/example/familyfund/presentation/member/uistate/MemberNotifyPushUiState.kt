package com.example.familyfund.presentation.member.uistate

sealed interface MemberNotifyPushUiState {



    data object Idel: MemberNotifyPushUiState
    data object Loading : MemberNotifyPushUiState


    data class Success(
        val message: String
    ) : MemberNotifyPushUiState


    data class Error(
        val message: String
    ) : MemberNotifyPushUiState
}