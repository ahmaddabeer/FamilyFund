package com.example.familyfund.presentation.auth

import com.example.familyfund.data.model.MemberModel


sealed interface LoginUIState {



    data object Idle: LoginUIState
    data object Loading : LoginUIState


    data class Sucess(

        val id: MemberModel
    ) : LoginUIState


    data class Empty(
        val message:String = "Check you ID else contact with Admin "
    ): LoginUIState

    data class Error(

        val message: String

    ) : LoginUIState
}