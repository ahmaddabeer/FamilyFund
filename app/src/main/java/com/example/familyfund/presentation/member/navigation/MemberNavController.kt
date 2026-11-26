package com.example.familyfund.presentation.member.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.familyfund.presentation.member.screen.MemberHomeScreen
import com.example.familyfund.presentation.member.screen.MemberLoanScreen
import com.example.familyfund.presentation.member.screen.MemberPaymentHistory
import com.example.familyfund.presentation.member.screen.MemberProfileScreen
import com.example.familyfund.presentation.member.screen.MemberSettingScreen

@Composable
fun MemberNavController(){


    val controller = rememberNavController()

    NavHost(
        navController = controller,
        startDestination = MemberRoute.MemberHomeScreen
    ) {

        composable<MemberRoute.MemberHomeScreen> {
            MemberHomeScreen(controller)

        }




        composable<MemberRoute.MemberProfileScreen> {
            MemberProfileScreen(controller)

        }

        composable<MemberRoute.MemberPaymentHistory> {

            MemberPaymentHistory(controller)
        }

        composable<MemberRoute.MemberLoanRecordScreen> {

            MemberLoanScreen(controller)


        }
    }
}