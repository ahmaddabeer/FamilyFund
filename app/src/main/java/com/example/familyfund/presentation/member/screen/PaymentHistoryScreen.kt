package com.example.familyfund.presentation.member.screen

import android.graphics.Paint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.familyfund.data.local.UserPreferences
import com.example.familyfund.presentation.admin.screen.*
import com.example.familyfund.presentation.member.component.MemberBottomNavigationBar
import com.example.familyfund.presentation.member.component.TransactionCard
import com.example.familyfund.presentation.member.uistate.MemberPaymentHistoryUiState
import com.example.familyfund.presentation.member.viewmodel.MemberViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemberPaymentHistory(controller: NavController) {


    val context = LocalContext.current
    val userpreference = remember { UserPreferences(context.applicationContext) }

    val user by userpreference.userSession.collectAsStateWithLifecycle(initialValue = null)

    val id = user?.id


    val viewmodel: MemberViewModel = viewModel()
    val paymentHistoryUiState by viewmodel.memberPaymentHistoryUiState.collectAsStateWithLifecycle()


    LaunchedEffect(id) {

        viewmodel.getMemberPaymetHistory(id.toString())
    }


    when(val state = paymentHistoryUiState){

        is MemberPaymentHistoryUiState.Loading->{

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {


                CircularProgressIndicator()

            }


        }


        is MemberPaymentHistoryUiState.Success->{

            Scaffold(
                containerColor = BackgroundColor,
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                "Payment History",
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
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {

                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(20.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = PrimaryColor
                            )
                        ) {
                            Column(
                                modifier = Modifier.padding(20.dp),
                                verticalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                Text(
                                    "Total Contribution",
                                    color = Color.White.copy(alpha = 0.85f),
                                    fontFamily = MediumFont,
                                    style = MaterialTheme.typography.titleSmall
                                )

                                val amo = state.allPaymentList.filter { it.status == "Complete" }.filter { it.memberId == id }.count()
                                val amount = (250*amo)
                                Text(
                                    "₹ $amount",
                                    color = Color.White,
                                    fontFamily = BoldFont,
                                    style = MaterialTheme.typography.headlineLarge
                                )

                                HorizontalDivider(
                                    color = Color.White.copy(alpha = 0.3f)
                                )

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Column {
                                        Text(
                                            "Successful Payments",
                                            color = Color.White.copy(alpha = 0.8f),
                                            style = MaterialTheme.typography.bodySmall
                                        )
                                        val completed = state.allPaymentList.filter { it.status == "Complete" }.filter { it.memberId == id }.count()
                                        Text(
                                            "$completed Payments",
                                            color = Color.White,
                                            fontFamily = BoldFont
                                        )
                                    }


//                                    val allpaymentHistory = state.allPaymentList.filter { it.memberId == id }
//
//
//                                    val pendingamt = (250*pendig)
                                    Column {
                                        Text(
                                            "Pending",
                                            color = Color.White.copy(alpha = 0.8f),
                                            style = MaterialTheme.typography.bodySmall
                                        )
//                                        Text(
//                                            "₹ $pendingamt",
//                                            color = Color.White,
//                                            fontFamily = BoldFont
//                                        )
                                    }
                                }
                            }
                        }
                    }

                    item {
                        Text(
                            "All Transactions",
                            style = MaterialTheme.typography.titleMedium,
                            fontFamily = BoldFont,
                            color = HeadingColor
                        )
                    }

                   items(
                       items = state.allPaymentList.filter { it.status == "Complete" }.filter { it.memberId == id },

                   ){item->

                       TransactionCard(item)


                   }
                }
            }
        }

        is MemberPaymentHistoryUiState.Error->{

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {


                Text(state.message)
            }
        }
    }
}