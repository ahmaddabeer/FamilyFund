package com.example.familyfund.presentation.admin.screen

import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.familyfund.data.model.MemberModel
import com.example.familyfund.data.model.PaymentRequestModel
import com.example.familyfund.presentation.admin.component.AdminBottomNavigationBar
import com.example.familyfund.presentation.admin.shimmerscreen.AdminMemberScreenUiState
import com.example.familyfund.presentation.admin.viewmodel.AdminViewModel
import com.example.familyfund.presentation.shummer.shimmerEffect
import dev.seyfarth.tablericons.TablerIcons
import dev.seyfarth.tablericons.outlined.ArrowBadgeLeft
import dev.seyfarth.tablericons.outlined.ArrowBadgeRight
import dev.seyfarth.tablericons.outlined.CalendarCheck
import dev.seyfarth.tablericons.outlined.ChevronRight
import dev.seyfarth.tablericons.outlined.MoodSmileBeam
import dev.seyfarth.tablericons.outlined.Search
import dev.seyfarth.tablericons.outlined.UserPlus
import java.util.Date
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminMembersScreen(controller: NavController) {

    val viewmode: AdminViewModel = viewModel()

    val adminMemberUiState by viewmode.adminMemberUiState.collectAsStateWithLifecycle()


    val availableMonths = viewmode.availableMonths

    var index by remember { mutableStateOf(0) }

    val lastMonth = availableMonths.lastIndex

    LaunchedEffect(Unit) {

        viewmode.getAvilableMoths()

    }









    LaunchedEffect(Unit) {
        viewmode.getAllMember()
    }

    var addMemberScreenActive by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }
    var selectedFilter by remember { mutableStateOf("All") }

    val filters = listOf("All", "Paid", "Pending", "Loan")

    LaunchedEffect(viewmode.filterDate) {

        viewmode.getMonthlyPaymentRecord()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Members",
                            fontFamily = BoldFont,
                            color = HeadingColor
                        )
                        Text(
                            text = "Manage your family members",
                            style = MaterialTheme.typography.labelSmall,
                            color = SecondaryTextColor
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = { addMemberScreenActive = true },
                        shape = RoundedCornerShape(12.dp),
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = SurfaceColor,
                            contentColor = PrimaryColor
                        )
                    ) {
                        Icon(
                            imageVector = TablerIcons.Outlined.UserPlus,
                            contentDescription = "Add Member"
                        )
                    }
                }
            )
        },
        bottomBar = {
            AdminBottomNavigationBar(controller)
        }
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(
                horizontal = 16.dp,
                vertical = 12.dp
            ),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            // Total Members
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            SurfaceColor,
                            RoundedCornerShape(16.dp)
                        )
                        .padding(16.dp)
                ) {
                    Text(
                        text = "TOTAL MEMBERS",
                        style = MaterialTheme.typography.labelMedium,
                        color = SecondaryTextColor,
                        fontFamily = MediumFont
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Row(
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {

                        when (val state = adminMemberUiState) {

                            is AdminMemberScreenUiState.Loading -> {

                                Box(
                                    modifier = Modifier
                                        .height(30.dp)
                                        .width(150.dp)
                                        .shimmerEffect()
                                )
                            }

                            is AdminMemberScreenUiState.Success -> {

                                Text(
                                    text = state.member.size.toString(),
                                    style = MaterialTheme.typography.headlineMedium,
                                    fontFamily = BoldFont,
                                    color = HeadingColor
                                )

                                Text(
                                    text = "Registered members",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = SecondaryTextColor,
                                    modifier = Modifier.padding(bottom = 5.dp)
                                )
                            }

                            is AdminMemberScreenUiState.Error -> {


                            }

                        }


                    }
                }
            }

            // Search
            item {
                androidx.compose.material3.OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text("Search by name or phone")
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = TablerIcons.Outlined.Search,
                            contentDescription = "Search"
                        )
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp)
                )
            }

            // Filters
            item {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    filters.forEach { filter ->
                        androidx.compose.material3.FilterChip(
                            selected = selectedFilter == filter,
                            onClick = { selectedFilter = filter },
                            label = { Text(filter) },
                            shape = RoundedCornerShape(10.dp)
                        )
                    }
                }
            }



            when (selectedFilter) {

                "All" -> {

                    // Member List Heading
                    item {
                        Text(
                            text = "Member List",
                            style = MaterialTheme.typography.titleMedium,
                            fontFamily = BoldFont,
                            color = HeadingColor
                        )
                    }
                    when (val state = adminMemberUiState) {

                        is AdminMemberScreenUiState.Loading -> {

                            items(10) {

                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(80.dp)
                                        .shimmerEffect()
                                )

                            }


                        }

                        is AdminMemberScreenUiState.Success -> {

                            if (viewmode.memberModelList.isNotEmpty()) {

                                items(
                                    items = state.member,
                                ) { member ->

                                    CardAllMember(member, {})
                                }
                            } else {

                                item {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(200.dp),
                                        contentAlignment = Alignment.Center
                                    ) {


                                        TextButton(onClick = {addMemberScreenActive = true},
                                            shape = RoundedCornerShape(12.dp),
                                            colors = ButtonDefaults.buttonColors(

                                                containerColor = PrimaryColor,
                                                contentColor = Color.White
                                            )
                                            ) {
                                            Text("Add Member", fontFamily = MediumFont)

                                        }
                                    }
                                }
                            }
                        }

                        is AdminMemberScreenUiState.Error -> {

                            item {

                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(100.dp),
                                    contentAlignment = Alignment.Center
                                ) {

                                    Text(state.message)
                                }
                            }
                        }
                    }


                }


                "Paid" -> {

                    // Member List Heading
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {


                            IconButton(onClick = {

                                if (index < lastMonth) {

                                    index++

                                    viewmode.onFilterDateChange(availableMonths[index].toLong())
                                }


                            }) {
                                Icon(
                                    imageVector = TablerIcons.Outlined.ArrowBadgeLeft,
                                    contentDescription = "Arrow Left",
                                    tint = PrimaryColor
                                )
                            }


                            Text(
                                text = "${
                                    SimpleDateFormat("yyyy-MM", Locale.getDefault()).format(
                                        Date(viewmode.filterDate)
                                    )
                                }",
                                style = MaterialTheme.typography.titleMedium,
                                fontFamily = BoldFont,
                                color = HeadingColor
                            )

                            IconButton(onClick = {

                                if (index > 0) {

                                    index--

                                    viewmode.onFilterDateChange(availableMonths[index].toLong())
                                }


                            }) {
                                Icon(
                                    imageVector = TablerIcons.Outlined.ArrowBadgeRight,
                                    contentDescription = "Arrow Right",
                                    tint = PrimaryColor
                                )
                            }


                        }
                    }

                    when (val state = adminMemberUiState) {

                        is AdminMemberScreenUiState.Loading -> {

                            items(10) {

                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(80.dp)
                                        .shimmerEffect()
                                )

                            }

                        }

                        is AdminMemberScreenUiState.Success -> {


                            val paid = state.monthlyPayment.filter { it.status == "Complete" }
                            if (paid.isNotEmpty()) {

                                items(

                                    items = paid
                                ) { paymentlist ->

                                    CardMontlyPayment(
                                        paymentlist,
                                        {},
                                        TablerIcons.Outlined.CalendarCheck
                                    )
                                }
                            } else {

                                item {

                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(200.dp),
                                        contentAlignment = Alignment.Center
                                    ) {


                                        Text(
                                            "No Activity",
                                            fontFamily = BoldFont,
                                            style = MaterialTheme.typography.titleLarge,
                                            color = SecondaryTextColor
                                        )

                                    }
                                }
                            }
                        }

                        is AdminMemberScreenUiState.Error -> {

                            item {

                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(150.dp),
                                    contentAlignment = Alignment.Center
                                ) {

                                    Text(state.message)
                                }
                            }
                        }
                    }


                }

                "Pending" -> {

                    // Member List Heading
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {


                            IconButton(onClick = {

                                if (index < lastMonth) {

                                    index++

                                    viewmode.onFilterDateChange(availableMonths[index].toLong())
                                }


                            }) {
                                Icon(
                                    imageVector = TablerIcons.Outlined.ArrowBadgeLeft,
                                    contentDescription = "Arrow Left",
                                    tint = PrimaryColor
                                )
                            }


                            Text(
                                text = "${
                                    SimpleDateFormat("yyyy-MM", Locale.getDefault()).format(
                                        Date(viewmode.filterDate)
                                    )
                                }",
                                style = MaterialTheme.typography.titleMedium,
                                fontFamily = BoldFont,
                                color = HeadingColor
                            )

                            IconButton(onClick = {

                                if (index > 0) {

                                    index--

                                    viewmode.onFilterDateChange(availableMonths[index].toLong())
                                }


                            }) {
                                Icon(
                                    imageVector = TablerIcons.Outlined.ArrowBadgeRight,
                                    contentDescription = "Arrow Right",
                                    tint = PrimaryColor
                                )
                            }


                        }
                    }

                    when (val state = adminMemberUiState) {

                        is AdminMemberScreenUiState.Loading -> {

                            items(10) {

                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(80.dp)
                                        .shimmerEffect()
                                )

                            }

                        }

                        is AdminMemberScreenUiState.Success -> {

                            val pending = state.member.filter { member ->
                                state.monthlyPayment.none { payment ->

                                    payment.memberId == member.memberId
                                }
                            }

                            if (pending.isNotEmpty()) {

                                items(

                                    items = pending
                                ) { paymentlist ->

                                    CardAllMember(paymentlist, {})
                                }


                            } else {

                                item {

                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(200.dp),
                                        contentAlignment = Alignment.Center
                                    ) {


                                        Text(
                                            "No Pending Payment",
                                            style = MaterialTheme.typography.titleLarge,
                                            color = SecondaryTextColor,
                                            fontFamily = BoldFont
                                        )


                                    }
                                }
                            }


                        }

                        is AdminMemberScreenUiState.Error -> {

                            item {

                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(150.dp),
                                    contentAlignment = Alignment.Center
                                ) {

                                    Text(state.message)
                                }
                            }
                        }
                    }


                }
            }


            item {
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }

    if (addMemberScreenActive) {
        AdminAddMemberScreen(
            { addMemberScreenActive = false },
            { viewmode.createMember() }
        )
    }
}


@Composable
private fun CardAllMember(

    member: MemberModel,
    onClick: () -> Unit = {},

    ) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Color.White,
                RoundedCornerShape(14.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 14.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(48.dp)
                .background(
                    SurfaceColor,
                    CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = member.name
                    .firstOrNull()
                    ?.uppercase() ?: "?",
                color = PrimaryColor,
                fontFamily = BoldFont,
                style = MaterialTheme.typography.titleMedium
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = member.name,
                style = MaterialTheme.typography.titleSmall,
                fontFamily = BoldFont,
                color = HeadingColor
            )

            Text(
                text = member.phone,
                style = MaterialTheme.typography.bodySmall,
                fontFamily = MediumFont,
                color = SecondaryTextColor
            )
        }

        IconButton(onClick = {}) {

            Icon(
                imageVector = TablerIcons.Outlined.ChevronRight,
                contentDescription = "View Details",
                tint = PrimaryColor,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}


@Composable
private fun CardMontlyPayment(

    paymentlist: PaymentRequestModel,
    onClick: () -> Unit = {},
    icon: ImageVector

) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Color.White,
                RoundedCornerShape(14.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 14.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(48.dp)
                .background(
                    SurfaceColor,
                    CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = paymentlist.memberName
                    .firstOrNull()
                    ?.uppercase() ?: "?",
                color = PrimaryColor,
                fontFamily = BoldFont,
                style = MaterialTheme.typography.titleMedium
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = paymentlist.memberName,
                style = MaterialTheme.typography.titleSmall,
                fontFamily = BoldFont,
                color = HeadingColor
            )

            Text(
                text = paymentlist.status,
                style = MaterialTheme.typography.bodySmall,
                fontFamily = MediumFont,
                color = SecondaryTextColor
            )
        }

        IconButton(onClick = {}) {

            Icon(
                imageVector = icon,
                contentDescription = "View Details",
                tint = GreenColor,
                modifier = Modifier.size(18.dp)

            )
        }
    }
}


