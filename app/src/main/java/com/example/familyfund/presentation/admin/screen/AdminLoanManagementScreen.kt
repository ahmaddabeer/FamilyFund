package com.example.familyfund.presentation.admin.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.familyfund.presentation.admin.component.AdminBottomNavigationBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminLoanManagementScreen(controller: NavController) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Loan Management",
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1E3A5F)
                        )
                        Text(
                            text = "Manage family loans",
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
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // Available Balance
            item {
                Column(
                    modifier = Modifier
                     .fillMaxWidth()
                     .background(
                      Color(0xFFEFF6FF),
                      RoundedCornerShape(16.dp)
                     )
                     .padding(18.dp)
                ) {
                    Text(
                        "AVAILABLE BALANCE",
                        color = Color(0xFF64748B),
                        fontSize = 12.sp
                    )

                    Text(
                        "₹45,000",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1E3A5F)
                    )

                    Text(
                        "Amount available to give as loan",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }

            // Loan Summary
            item {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    LoanSummaryCard(
                        title = "Total Given",
                        amount = "₹80,000",
                        modifier = Modifier.weight(1f),
                        color = Color(0xFF16855A)
                    )

                    LoanSummaryCard(
                        title = "Total Returned",
                        amount = "₹35,000",
                        modifier = Modifier.weight(1f),
                        color = Color(0xFF2563EB)
                    )
                }
            }

            // Outstanding
            item {
                Column(
                    modifier = Modifier
                     .fillMaxWidth()
                     .background(
                      Color(0xFFFFF7ED),
                      RoundedCornerShape(14.dp)
                     )
                     .padding(16.dp)
                ) {
                    Text(
                        "OUTSTANDING LOAN",
                        color = Color(0xFFC2410C),
                        fontSize = 12.sp
                    )

                    Text(
                        "₹45,000",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF9A3412)
                    )

                    Text(
                        "Amount yet to be returned",
                        color = Color(0xFF9A3412),
                        fontSize = 12.sp
                    )
                }
            }

            // Next in Queue
            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        "Next in Loan Queue",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1E3A5F)
                    )

                    Column(
                        modifier = Modifier
                         .fillMaxWidth()
                         .background(
                          Color.White,
                          RoundedCornerShape(14.dp)
                         )
                         .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                "Rahul Kumar",
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                "Next",
                                color = Color(0xFFD97706),
                                fontSize = 12.sp
                            )
                        }

                        Text(
                            "Requested Amount",
                            color = Color.Gray,
                            fontSize = 12.sp
                        )

                        Text(
                            "₹20,000",
                            fontSize = 21.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1E3A5F)
                        )

                        Button(
                            onClick = {},
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text("View Loan Request")
                        }
                    }
                }
            }

            // Active Loans
            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        "Active Loans",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1E3A5F)
                    )

                    Column(
                        modifier = Modifier
                         .fillMaxWidth()
                         .background(
                          Color.White,
                          RoundedCornerShape(14.dp)
                         )
                         .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text(
                            "Aman Ahmad",
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            "Loan Amount: ₹30,000",
                            color = Color.Gray,
                            fontSize = 13.sp
                        )

                        HorizontalDivider(
                            color = Color(0xFFF1F5F9)
                        )

                        LoanInfoRow("Monthly Return", "₹5,000")
                        LoanInfoRow("Total Returned", "₹10,000")
                        LoanInfoRow("Remaining", "₹20,000")

                        LinearProgressIndicator(
                            progress = { 10f / 30f },
                            modifier = Modifier
                             .fillMaxWidth()
                             .height(7.dp),
                            color = Color(0xFF16855A),
                            trackColor = Color(0xFFE2E8F0)
                        )

                        Text(
                            "2 of 6 installments completed",
                            color = Color.Gray,
                            fontSize = 11.sp
                        )
                    }
                }
            }

            // Interest Free Note
            item {
                Text(
                    "All loans are interest-free • 0% Interest",
                    modifier = Modifier.fillMaxWidth(),
                    color = Color(0xFF16855A),
                    fontSize = 12.sp
                )
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
private fun LoanSummaryCard(
    title: String,
    amount: String,
    modifier: Modifier = Modifier,
    color: Color
) {
    Column(
        modifier = modifier
         .background(
          Color.White,
          RoundedCornerShape(14.dp)
         )
         .padding(14.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            title,
            fontSize = 12.sp,
            color = Color.Gray
        )

        Text(
            amount,
            fontSize = 19.sp,
            fontWeight = FontWeight.Bold,
            color = color
        )
    }
}

@Composable
private fun LoanInfoRow(
    title: String,
    amount: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            title,
            color = Color.Gray,
            fontSize = 13.sp
        )

        Text(
            amount,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF334155),
            fontSize = 13.sp
        )
    }
}
