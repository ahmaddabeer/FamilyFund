package com.example.familyfund.presentation.admin.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.familyfund.presentation.admin.navigation.AdminRoute
import com.example.familyfund.presentation.admin.screen.MediumFont
import com.example.familyfund.presentation.admin.screen.PrimaryColor
import com.example.familyfund.presentation.admin.screen.SecondaryTextColor
import com.example.familyfund.presentation.admin.viewmodel.AdminViewModel
import dev.seyfarth.tablericons.TablerIcons
import dev.seyfarth.tablericons.outlined.ChartBarPopular
import dev.seyfarth.tablericons.outlined.Home
import dev.seyfarth.tablericons.outlined.Hourglass
import dev.seyfarth.tablericons.outlined.HourglassHigh
import dev.seyfarth.tablericons.outlined.LayoutDashboard
import dev.seyfarth.tablericons.outlined.Users
import dev.seyfarth.tablericons.outlined.UsersGroup
import dev.seyfarth.tablericons.outlined.Wallet
import okhttp3.Route

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AdminBottomNavigationBar(controller: NavController) {


    val viewmodel: AdminViewModel = viewModel()

    val paymentrequestList = viewmodel.paymentList

    LaunchedEffect(paymentrequestList) {

        viewmodel.getAllPaymentList()
    }

    val Navigationitem = listOf(

        Item( TablerIcons.Outlined.LayoutDashboard,"Home", AdminRoute.AdminHomeScreen),
        Item( TablerIcons.Outlined.Users, "Members",AdminRoute.AdminMembersScreen),
        Item( TablerIcons.Outlined.Wallet, "Loans",AdminRoute.AdminLoanManagementScreen),
        Item(TablerIcons.Outlined.HourglassHigh, "Requests",AdminRoute.AdminVarifyPaymentScreen)
    )


    val navBackStackEntry by controller.currentBackStackEntryAsState()

    val currentDestination = navBackStackEntry?.destination





    NavigationBar(containerColor = Color.White) {

        Navigationitem.forEach { item ->

            val isSelected = currentDestination?.hasRoute(item.adminRoute::class)== true

            NavigationBarItem(
                selected = isSelected,
                label = { Text(item.title, fontFamily = MediumFont,color = if(isSelected)PrimaryColor else SecondaryTextColor) },
                icon = {


                    if(item.icon == TablerIcons.Outlined.HourglassHigh) {

                        if(paymentrequestList.filter{it.status == "Verification in progress."}.count() >0){


                            BadgedBox(
                                badge = {

                                    Badge(containerColor = Color.Red,
                                        contentColor = PrimaryColor)
                                }
                            ){}


                        }


                    }



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
    val title: String,
    val adminRoute: AdminRoute
)