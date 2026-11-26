package com.example.familyfund.presentation.admin.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.familyfund.presentation.admin.component.AdminBottomNavigationBar
import com.example.familyfund.presentation.admin.viewmodel.AdminViewModel


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminVarifyPaymentScreen(controller: NavController) {



    val viewmodel: AdminViewModel = viewModel()

    val paymentrequestList = viewmodel.paymentList

    LaunchedEffect(paymentrequestList) {

        viewmodel.getAllPaymentList()
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Payment Verification",
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1E3A5F)
                        )

                        Text(
                            text = "Review pending payments",
                            fontSize = 12.sp,
                            color = Color.Gray
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
                .fillMaxSize()
                .background(Color(0xFFF8FAFC))
                .padding(innerPadding),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            item {
                Text(
                    text = "Pending Requests (${paymentrequestList.filter { it.status == "Verification in progress." }.count()})",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E3A5F)
                )
            }

            items(
                items = paymentrequestList.filter { it.status == "Verification in progress." },
                key = { it.memberId }
            ) { payment ->

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Color.White,
                            RoundedCornerShape(14.dp)
                        )
                        .padding(14.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    // User Details
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = payment.memberName,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1E3A5F)
                        )

                        Text(
                            text = payment.memberId,
                            fontSize = 13.sp,
                            color = Color.Gray
                        )
                    }

                    // Verification Status
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Verification Status",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )

                        Text(
                            text = payment.status,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFFD97706),
                            modifier = Modifier
                                .background(
                                    Color(0xFFFFF7ED),
                                    RoundedCornerShape(6.dp)
                                )
                                .padding(
                                    horizontal = 10.dp,
                                    vertical = 5.dp
                                )
                        )
                    }

                    // Yes / No Buttons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Button(
                            onClick = {

                                viewmodel.updatePaymentRequestStatus(payment.memberId)




                            },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(9.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF16855A)
                            )
                        ) {
                            Text("Yes")
                        }

                        Button(
                            onClick = {},
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(9.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFDC2626)
                            )
                        ) {
                            Text("No")
                        }
                    }
                }
            }
        }
    }
}
