package com.example.familyfund.presentation.member.component

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
import com.example.familyfund.presentation.admin.screen.PrimaryColor
import com.example.familyfund.presentation.admin.screen.SecondaryTextColor
import com.example.familyfund.presentation.member.navigation.MemberRoute
import dev.seyfarth.tablericons.TablerIcons
import dev.seyfarth.tablericons.outlined.History
import dev.seyfarth.tablericons.outlined.Home
import dev.seyfarth.tablericons.outlined.MoneybagMinus
import dev.seyfarth.tablericons.outlined.User


@Composable
fun MemberBottomNavigationBar(controller: NavController) {

    val navigationItems = listOf(

        Item(TablerIcons.Outlined.Home, MemberRoute.MemberHomeScreen),
        Item(TablerIcons.Outlined.History, MemberRoute.MemberPaymentHistory),
        Item(TablerIcons.Outlined.MoneybagMinus, MemberRoute.MemberLoanRecordScreen),
        Item(TablerIcons.Outlined.User, MemberRoute.MemberProfileScreen)

    )


    val backStackEntry by controller.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination


    NavigationBar(
    ) {


        navigationItems.forEach { item ->


            val selectedItem = currentDestination?.hasRoute(item.route::class) == true

            NavigationBarItem(

                selected = selectedItem,
                onClick = {
                    controller.navigate(route = item.route) {
                        popUpTo(controller.graph.findStartDestination().id) {

                            saveState = true
                        }


                        launchSingleTop = true
                        restoreState = true
                    }

                },
                label = null,
                alwaysShowLabel = false,
                icon = { Icon(imageVector = item.icon, "helo") },
                colors = NavigationBarItemDefaults.colors(

                    indicatorColor = Color.Transparent,
                    selectedIconColor = PrimaryColor,
                    unselectedIconColor = SecondaryTextColor
                )


            )
        }
    }


}

data class Item(

    val icon: ImageVector,
    val route: MemberRoute
)