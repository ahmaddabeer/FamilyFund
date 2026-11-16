package com.example.familyfund.presentation.admin.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.familyfund.presentation.admin.screen.HomeScreen
import com.example.familyfund.presentation.admin.screen.MembersScreen
import com.example.familyfund.presentation.admin.screen.PaymentsScreen
import com.example.familyfund.presentation.admin.screen.ReportsScreen

@Composable
 fun NavController() {



     val controller = rememberNavController()


    NavHost(
        navController = controller,
        startDestination = AdminRoute.HomeScreen
    ){


        composable<AdminRoute.HomeScreen>{

            HomeScreen(controller)
        }


        composable<AdminRoute.ReportsScreen>{

            ReportsScreen()
        }

        composable<AdminRoute.MembersScreen>{

            MembersScreen(controller)
        }

        composable<AdminRoute.PaymentsScreen>{

            PaymentsScreen()
        }
    }

}