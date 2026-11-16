package com.example.familyfund.presentation.admin.navigation

import kotlinx.serialization.Serializable


@Serializable
sealed class AdminRoute {


    @Serializable
    object HomeScreen : AdminRoute()


    @Serializable
    object MembersScreen : AdminRoute()


    @Serializable
    object PaymentsScreen : AdminRoute()


    @Serializable
    object ReportsScreen : AdminRoute()
}