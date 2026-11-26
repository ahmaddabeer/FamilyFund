package com.example.familyfund.presentation.member.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.familyfund.data.local.UserPreferences
import com.example.familyfund.presentation.admin.component.TransactionCard
import com.example.familyfund.presentation.admin.screen.BackgroundColor
import com.example.familyfund.presentation.admin.screen.BoldFont
import com.example.familyfund.presentation.admin.screen.HeadingColor
import com.example.familyfund.presentation.admin.screen.MediumFont
import com.example.familyfund.presentation.admin.screen.PrimaryColor
import com.example.familyfund.presentation.admin.screen.SecondaryTextColor
import com.example.familyfund.presentation.member.component.MemberBottomNavigationBar
import com.example.familyfund.presentation.member.uistate.MemberHomeUiState
import com.example.familyfund.presentation.member.viewmodel.MemberViewModel
import java.time.Instant
import java.time.YearMonth
import java.time.ZoneId

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemberHomeScreen(controller: NavController) {

    val showNotificationDialog = remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current

    val userPreferences = remember { UserPreferences(context.applicationContext) }
    val userId by userPreferences.userSession.collectAsStateWithLifecycle(initialValue = null)
    val id = userId?.id
    val name = userId?.name


    val viewmodel: MemberViewModel = viewModel()
    val memberUiState = viewmodel.memberHomeUiState.collectAsStateWithLifecycle()


    val currentMonth = YearMonth.now().toString()

    LaunchedEffect(id) {

        viewmodel.getMemberDetails(id.toString())
    }


    val monthlyContributionsList by remember {
        mutableStateOf(false)


    }

    when (val state = memberUiState.value) {

        is MemberHomeUiState.Loading -> {

            MemberHomeShimmerScreen()


        }

        is MemberHomeUiState.Sucess -> {

            val memberpaid = state.paymentList.filter { it.status == "Complete" }.count()
            val memberpending = state.paymentList.filter { it.status == "Pending" }.count()



            Scaffold(

                containerColor = BackgroundColor,

                topBar = {

                    TopAppBar(

                        title = {

                            Text(
                                "FamilyFund",
                                style = MaterialTheme.typography.titleLarge,
                                fontFamily = BoldFont,
                                color = HeadingColor
                            )

                        }

                    )

                },

                bottomBar = {

                    MemberBottomNavigationBar(controller)
                }

            ) { innerPadding ->

                LazyColumn(

                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(horizontal = 16.dp, vertical = 12.dp),

                    verticalArrangement = Arrangement.spacedBy(8.dp)

                ) {

                    item {

                        OverviewCard(memberpaid, memberpending)

                        Spacer(modifier = Modifier.height(18.dp))

                        val mypaymentstatus = state.paymentList.find { it.memberId == id }

                        val requestMonth = Instant.ofEpochMilli(mypaymentstatus?.createdAt ?: 0L)
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate()
                            .let { YearMonth.from(it).toString() }

                        TextButton(

                            onClick = {
                                showNotificationDialog.value = true
                            },

                            enabled = when {

                                mypaymentstatus?.status == "Complete" && requestMonth == currentMonth -> false
                                mypaymentstatus?.status == "Verification in progress." && requestMonth == currentMonth -> false
                                else -> true


                            },

                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),

                            shape = RoundedCornerShape(14.dp),

                            colors = ButtonDefaults.buttonColors(
                                containerColor = PrimaryColor
                            )

                        ) {


                            Text(
                                text = mypaymentstatus?.status ?: "Pay Now ",
                                fontFamily = MediumFont,
                                style = MaterialTheme.typography.labelLarge,
                                color = if (mypaymentstatus?.status == "Complete") Color.Green.copy(
                                    0.5f
                                ) else Color.White
                            )

                        }

                        Spacer(modifier = Modifier.height(18.dp))

                        Text(
                            "Monthly Contributions",
                            style = MaterialTheme.typography.titleMedium,
                            fontFamily = BoldFont,
                            color = HeadingColor
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                    }

                    if (monthlyContributionsList) {

                        items(3) {

                            TransactionCard()

                        }
                    } else {

                        item {

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(150.dp),
                                contentAlignment = Alignment.Center
                            ) {

                                Text(
                                    "No Transaction Available",
                                    fontFamily = MediumFont,
                                    color = SecondaryTextColor
                                )
                            }
                        }
                    }

                }

            }


            if (showNotificationDialog.value) {

                MemberNotifyPushScreen(
                    { showNotificationDialog.value = false },
                    name.toString(),
                    id.toString(),
                    context,
                    viewmodel


                )

            }
        }

        is MemberHomeUiState.Error -> {

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

                Text(state.message)
            }
        }
    }


}


@Composable
private fun OverviewCard(

    paid: Int,
    pending: Int,

    ) {

    Card(

        modifier = Modifier.fillMaxWidth(),

        shape = RoundedCornerShape(22.dp),

        colors = CardDefaults.cardColors(
            containerColor = PrimaryColor,
            contentColor = Color.White
        ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )

    ) {

        Column(

            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 22.dp),

            verticalArrangement = Arrangement.spacedBy(16.dp)

        ) {

            Text(
                text = "Available Balance",
                style = MaterialTheme.typography.titleSmall,
                fontFamily = MediumFont,
                color = Color.White.copy(alpha = 0.85f)
            )

            Text(
                text = "₹ 1,250.00",
                style = MaterialTheme.typography.headlineLarge,
                fontFamily = BoldFont,
                color = Color.White
            )

            HorizontalDivider(
                color = Color.White.copy(alpha = 0.30f),
                thickness = 1.dp
            )

            Row(

                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement = Arrangement.spacedBy(12.dp)

            ) {

                Column(

                    modifier = Modifier.weight(1f),

                    verticalArrangement = Arrangement.spacedBy(6.dp)

                ) {

                    Text(
                        text = "Paid Members",
                        style = MaterialTheme.typography.bodySmall,
                        fontFamily = MediumFont,
                        color = Color.White.copy(alpha = 0.85f)
                    )

                    Text(
                        text = "$paid Members",
                        style = MaterialTheme.typography.titleMedium,
                        fontFamily = BoldFont,
                        color = Color.White
                    )

                }

                Column(

                    modifier = Modifier.weight(1f),

                    verticalArrangement = Arrangement.spacedBy(6.dp)

                ) {

                    Text(
                        text = "Pending Members",
                        style = MaterialTheme.typography.bodySmall,
                        fontFamily = MediumFont,
                        color = Color.White.copy(alpha = 0.85f)
                    )

                    Text(
                        text = "$pending Members",
                        style = MaterialTheme.typography.titleMedium,
                        fontFamily = BoldFont,
                        color = Color.White
                    )

                }

            }

        }

    }

}