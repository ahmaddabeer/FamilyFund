package com.example.familyfund.presentation.admin.navigation

import kotlinx.serialization.Serializable


@Serializable
sealed class AdminRoute {


    @Serializable
    object AdminHomeScreen : AdminRoute()


    @Serializable
    object AdminMembersScreen : AdminRoute()


    @Serializable
    object AdminLoanManagementScreen : AdminRoute()


    @Serializable
    object AdminVarifyPaymentScreen : AdminRoute()
}