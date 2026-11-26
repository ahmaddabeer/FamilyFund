package com.example.familyfund.presentation.admin.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.familyfund.presentation.admin.screen.AdminHomeScreen
import com.example.familyfund.presentation.admin.screen.AdminLoanManagementScreen
import com.example.familyfund.presentation.admin.screen.AdminMembersScreen
import com.example.familyfund.presentation.admin.screen.AdminVarifyPaymentScreen

@Composable
fun AdminNavController() {


    val controller = rememberNavController()


    NavHost(
        navController = controller,
        startDestination = AdminRoute.AdminHomeScreen
    ) {


        composable<AdminRoute.AdminHomeScreen> {

            AdminHomeScreen(controller)
        }


        composable<AdminRoute.AdminVarifyPaymentScreen> {

            AdminVarifyPaymentScreen(controller)
        }

        composable<AdminRoute.AdminMembersScreen> {

            AdminMembersScreen(controller)
        }

        composable<AdminRoute.AdminLoanManagementScreen> {

            AdminLoanManagementScreen(controller)
        }
    }

}