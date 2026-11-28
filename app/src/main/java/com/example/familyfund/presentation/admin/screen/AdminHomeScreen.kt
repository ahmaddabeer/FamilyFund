package com.example.familyfund.presentation.admin.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.familyfund.R
import com.example.familyfund.data.model.PaymentRequestModel
import com.example.familyfund.presentation.admin.AdminHomeScreenUiState
import com.example.familyfund.presentation.admin.component.AdminBottomNavigationBar
import com.example.familyfund.presentation.admin.viewmodel.AdminViewModel
import com.example.familyfund.presentation.member.screen.AdminHomeShimmerScreen
import dev.seyfarth.tablericons.TablerIcons
import dev.seyfarth.tablericons.outlined.Clock
import dev.seyfarth.tablericons.outlined.Moneybag
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.Locale

// Fonts
val BoldFont = FontFamily(
    Font(R.font.popin_bold, FontWeight.Bold)
)

val MediumFont = FontFamily(
    Font(R.font.medium, FontWeight.Medium)
)

// Colors
val PrimaryColor = Color(0xFF3B82F6)
val HeadingColor = Color(0xFF1E3A5F)
val SurfaceColor = Color(0xFFEFF6FF)
val BackgroundColor = Color(0xFFF8FAFC)
val PrimaryTextColor = Color(0xFF334155)
val SecondaryTextColor = Color(0xFF94A3B8)

 val CardColor = Color.White
 val GreenColor = Color(0xFF16A269)
val OrangeColor = Color(0xFFEEB42E)


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminHomeScreen(controller: NavHostController) {



    val viewModel: AdminViewModel = viewModel()
    val adminHomeScreenUiState by viewModel.adminHomeScreenUiState.collectAsStateWithLifecycle()




    LaunchedEffect(Unit) {

        viewModel.getAllCurrentMonthPaymentForAdminHomeScreen()
    }


    when(val state = adminHomeScreenUiState){

        is AdminHomeScreenUiState.Loading ->{

            AdminHomeShimmerScreen()
        }

        is AdminHomeScreenUiState.Success ->{

            Scaffold(
                containerColor = BackgroundColor,

                topBar = {
                    TopAppBar(
                        title = {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(3.dp)
                            ) {
                                Text(
                                    text = "Dashboard",
                                    fontSize = 19.sp,
                                    fontFamily = BoldFont,
                                    color = HeadingColor
                                )

                                Text(
                                    text = SimpleDateFormat(
                                        "EEEE, dd MMMM yyyy",
                                        Locale.getDefault()
                                    ).format(Date(System.currentTimeMillis())),
                                    fontSize = 11.sp,
                                    fontFamily = MediumFont,
                                    color = SecondaryTextColor
                                )
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = BackgroundColor
                        )
                    )
                },

                bottomBar = {
                    AdminBottomNavigationBar(controller)
                }

            ) { innerPadding ->

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(horizontal = 16.dp, vertical = 14.dp),

                    verticalArrangement = Arrangement.spacedBy(12.dp)

                ) {

                    item {

                        OverviewCard(state.allMonthPaymentRecord)

                        Spacer(modifier = Modifier.height(22.dp))

                        // This Month Overview
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Text(
                                text = "This Month Overview",
                                fontSize = 15.sp,
                                fontFamily = BoldFont,
                                color = HeadingColor
                            )

                            Text(
                                text = SimpleDateFormat(
                                    "MMMM yyyy",
                                    Locale.getDefault()
                                ).format(Date(System.currentTimeMillis())),
                                fontSize = 11.sp,
                                fontFamily = MediumFont,
                                color = SecondaryTextColor
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))


                        var totalBalanceCount = state.allPaymentRecordCurrentMonth.filter { it.status == "Complete" }.count()
                        var totalBalance = (totalBalanceCount * 250)
                        val totalMember = state.allmember.count()*250
                        val  percentage = (totalBalance/totalMember)*100
                        // Monthly Collection
                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {




                                Text(
                                    text = totalBalance.toString(),
                                    fontSize = 23.sp,
                                    fontFamily = BoldFont,
                                    color = HeadingColor
                                )

                                Text(
                                    text = "$percentage%",
                                    fontSize = 15.sp,
                                    fontFamily = BoldFont,
                                    color = GreenColor
                                )
                            }

                            Spacer(modifier = Modifier.height(3.dp))

                            Text(
                                text = "$totalBalanceCount out of 12 members paid",
                                fontSize = 11.sp,
                                fontFamily = MediumFont,
                                color = SecondaryTextColor
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            // Progress Bar
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(7.dp)
                                    .clip(RoundedCornerShape(50.dp))
                                    .background(Color(0xFFE2E8F0))
                            ) {

                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth(10f / 12f)
                                        .fillMaxHeight()
                                        .background(GreenColor)
                                )
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {

                                Text(
                                    text = "Collected: $totalBalanceCount",
                                    fontSize = 11.sp,
                                    fontFamily = MediumFont,
                                    color = GreenColor
                                )

                                Text(
                                    text = "Remaining: ${12-totalBalanceCount}",
                                    fontSize = 11.sp,
                                    fontFamily = MediumFont,
                                    color = OrangeColor
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))



                        // Pending & Active Loans
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {

                            ThisMonthCard(
                                icon = TablerIcons.Outlined.Clock,
                                type = "Verify",
                                amount = "${state.allPaymentRecordCurrentMonth.filter { it.status == "Verification in progress." }.count()}",
                                desc = "Pending to varify",
                                md = Modifier.weight(1f)
                            )

                            ThisMonthCard(
                                icon = TablerIcons.Outlined.Moneybag,
                                type = "Active Loans",
                                amount = "01",
                                desc = "Running Loans",
                                md = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }



        }

        is AdminHomeScreenUiState.Error ->{

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){

                Text(state.message)
            }
        }
    }
}


// -----------------------------------------
// Overview Card
// -----------------------------------------

@Composable
private fun OverviewCard(allMonthPaymentRecord: List<PaymentRequestModel>) {


    val amountcount = allMonthPaymentRecord.filter { it.status == "Complete" }.count()


    Card(
        modifier = Modifier.fillMaxWidth(),

        shape = RoundedCornerShape(20.dp),

        colors = CardDefaults.cardColors(
            containerColor = PrimaryColor,
            contentColor = Color.White
        ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )

    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 20.dp),

            verticalArrangement = Arrangement.spacedBy(14.dp)

        ) {

            Text(
                text = "Available Balance",
                fontSize = 12.sp,
                fontFamily = MediumFont,
                color = Color.White.copy(alpha = 0.85f)
            )

            Text(
                text = "₹ ${amountcount*250} ",
                fontSize = 27.sp,
                fontFamily = BoldFont,
                color = Color.White
            )

            HorizontalDivider(
                color = Color.White.copy(alpha = 0.25f),
                thickness = 1.dp
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {

                    Text(
                        text = "Total Collected",
                        fontSize = 11.sp,
                        fontFamily = MediumFont,
                        color = Color.White.copy(alpha = 0.85f)
                    )

                    Text(
                        text = "2,000",
                        fontSize = 16.sp,
                        fontFamily = BoldFont,
                        color = Color.White
                    )
                }

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {

                    Text(
                        text = "Loan Outstanding",
                        fontSize = 11.sp,
                        fontFamily = MediumFont,
                        color = Color.White.copy(alpha = 0.85f)
                    )

                    Text(
                        text = "40,000",
                        fontSize = 16.sp,
                        fontFamily = BoldFont,
                        color = Color.White
                    )
                }
            }
        }
    }
}


// -----------------------------------------
// This Month Card
// -----------------------------------------

@Composable
fun ThisMonthCard(
    icon: ImageVector,
    type: String,
    amount: String,
    desc: String,
    md: Modifier
) {

    OutlinedCard(
        modifier = md,

        shape = RoundedCornerShape(16.dp),

        border = BorderStroke(
            1.dp,
            color = Color(0xFFEAF0F6)
        ),

        colors = CardDefaults.outlinedCardColors(
            containerColor = CardColor
        ),

        elevation = CardDefaults.outlinedCardElevation(
            defaultElevation = 2.dp
        )

    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 15.dp),

            verticalArrangement = Arrangement.spacedBy(11.dp)

        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .clip(RoundedCornerShape(9.dp))
                        .background(SurfaceColor),
                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        imageVector = icon,
                        contentDescription = desc,
                        tint = PrimaryColor,
                        modifier = Modifier.size(17.dp)
                    )
                }

                Text(
                    text = type,
                    fontSize = 11.sp,
                    fontFamily = MediumFont,
                    color = PrimaryTextColor
                )
            }

            Text(
                text = amount,
                fontSize = 23.sp,
                fontFamily = BoldFont,
                color = HeadingColor
            )

            Text(
                text = desc,
                fontSize = 10.sp,
                fontFamily = MediumFont,
                color = SecondaryTextColor
            )
        }
    }
}
