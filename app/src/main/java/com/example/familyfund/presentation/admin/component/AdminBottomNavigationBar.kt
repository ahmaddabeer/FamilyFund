package com.example.familyfund.presentation.admin.component

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.familyfund.presentation.admin.navigation.AdminRoute
import com.example.familyfund.presentation.admin.screen.PrimaryColor
import com.example.familyfund.presentation.admin.screen.SecondaryTextColor
import dev.seyfarth.tablericons.TablerIcons
import dev.seyfarth.tablericons.outlined.ChartBarPopular
import dev.seyfarth.tablericons.outlined.Home
import dev.seyfarth.tablericons.outlined.UsersGroup
import dev.seyfarth.tablericons.outlined.Wallet

@Composable
fun BottomNavigationBar(controller: NavController) {


    val Navigationitem = listOf(

        Item( TablerIcons.Outlined.Home, AdminRoute.HomeScreen),
        Item( TablerIcons.Outlined.UsersGroup, AdminRoute.MembersScreen),
        Item( TablerIcons.Outlined.Wallet, AdminRoute.PaymentsScreen),
        Item(TablerIcons.Outlined.ChartBarPopular, AdminRoute.ReportsScreen)
    )


    val navBackStackEntry by controller.currentBackStackEntryAsState()

    val currentDestination = navBackStackEntry?.destination





    NavigationBar(containerColor = Color.White) {

        Navigationitem.forEach { item ->

            val isSelected = currentDestination?.hasRoute(item.adminRoute::class)== true

            NavigationBarItem(
                selected = isSelected,
                label = null,
                icon = {
                    Icon(imageVector = item.icon, contentDescription = "", tint = if(isSelected) PrimaryColor else SecondaryTextColor)


                },

                onClick = {

                    controller.navigate(item.adminRoute){

                        popUpTo(controller.graph.findStartDestination().id){

                            saveState = false
                        }


                        launchSingleTop= true
                        restoreState = false
                    }

                },

                colors = NavigationBarItemDefaults.colors(

                    indicatorColor = Color.Transparent
                )
            )
        }


    }


}


data class Item(


    val icon: ImageVector,
    val adminRoute: AdminRoute
)