package com.example.familyfund.presentation.member.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.familyfund.presentation.member.navigation.MemberRoute
import dev.seyfarth.tablericons.TablerIcons
import dev.seyfarth.tablericons.outlined.Home


@Composable
fun MemberBottomNavigationBar(){

    val item = listOf<item>(

        item(TablerIcons.Outlined.Home, MemberRoute)
    )



}

data class item(

    val icon: ImageVector,
    val route: MemberRoute
)