package com.example.familyfund.presentation.member.navigation

import kotlinx.serialization.Serializable


@Serializable
sealed class MemberRoute {


    @Serializable
    object MemberHomeScreen: MemberRoute()


    @Serializable
    object MemberProfileScreen: MemberRoute()



    @Serializable
    object MemberPaymentHistory: MemberRoute()






    @Serializable
    object MemberLoanRecordScreen: MemberRoute()




}