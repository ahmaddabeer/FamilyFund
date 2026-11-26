package com.example.familyfund.presentation.member.uistate

import com.example.familyfund.data.model.MemberModel
import java.lang.reflect.Member

sealed interface MemberProfileUiState{


    data object Loading: MemberProfileUiState

    data class Success(
        val member: MemberModel?
    ): MemberProfileUiState


    data class Error(
        val message:String
    ): MemberProfileUiState
}