package com.example.familyfund.presentation.member.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.familyfund.presentation.admin.screen.BackgroundColor
import com.example.familyfund.presentation.admin.screen.BoldFont
import com.example.familyfund.presentation.admin.screen.HeadingColor
import com.example.familyfund.presentation.admin.screen.MediumFont
import com.example.familyfund.presentation.admin.screen.PrimaryColor
import com.example.familyfund.presentation.admin.screen.PrimaryTextColor
import com.example.familyfund.presentation.admin.screen.SecondaryTextColor
import com.example.familyfund.presentation.admin.screen.SurfaceColor
import com.example.familyfund.presentation.member.component.MemberBottomNavigationBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemberLoanScreen(controller: NavController) {

    Scaffold(containerColor = BackgroundColor, topBar = {
        TopAppBar(
            title = {
                Column() {
                    Text(
                        "Community Loans",
                        fontFamily = BoldFont,
                        color = HeadingColor,
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(
                        "All community member's loan records",
                        fontFamily = MediumFont,
                        style = MaterialTheme.typography.labelSmall,
                        color = SecondaryTextColor
                    )
                }
            })

    }, bottomBar = {

        MemberBottomNavigationBar(controller)
    }) { innerPadding ->

        LazyColumn(){
            item {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(18.dp)
                ) {


                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = PrimaryColor
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            verticalArrangement = Arrangement.spacedBy(20.dp) // Upar aur neeche ki row ke beech barabar gap
                        ) {
                            // Row 1
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(16.dp), // Left aur Right column ke beech gap
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // Top-Left Item
                                Column(
                                    modifier = Modifier.weight(1f), // 50% width lega
                                    horizontalAlignment = Alignment.Start,
                                    verticalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Text(
                                        text = "Remaining Amount",
                                        fontFamily = MediumFont,
                                        style = MaterialTheme.typography.labelSmall,
                                        color = Color.White.copy(alpha = 0.8f)
                                    )
                                    Text(
                                        text = "52,333",
                                        fontFamily = BoldFont,
                                        style = MaterialTheme.typography.titleLarge,
                                        color = Color.White
                                    )
                                }

                                // Top-Right Item
                                Column(
                                    modifier = Modifier.weight(1f), // 50% width lega (Exact alignment ke liye)
                                    horizontalAlignment = Alignment.Start,
                                    verticalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Text(
                                        text = "Total Loan Given",
                                        fontFamily = MediumFont,
                                        style = MaterialTheme.typography.labelSmall,
                                        color = Color.White.copy(alpha = 0.8f)
                                    )
                                    Text(
                                        text = "52,333",
                                        fontFamily = BoldFont,
                                        style = MaterialTheme.typography.titleLarge,
                                        color = Color.White
                                    )
                                }
                            }

                            HorizontalDivider(modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp))

                            // Row 2
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // Bottom-Left Item
                                Column(
                                    modifier = Modifier.weight(1f),
                                    horizontalAlignment = Alignment.Start,
                                    verticalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Text(
                                        text = "Total Members",
                                        fontFamily = MediumFont,
                                        style = MaterialTheme.typography.labelSmall,
                                        color = Color.White.copy(alpha = 0.8f)
                                    )
                                    Text(
                                        text = "12",
                                        fontFamily = BoldFont,
                                        style = MaterialTheme.typography.titleLarge,
                                        color = Color.White
                                    )
                                }



                                // Bottom-Right Item
                                Column(
                                    modifier = Modifier.weight(1f),
                                    horizontalAlignment = Alignment.Start,
                                    verticalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Text(
                                        text = "Active Loans",
                                        fontFamily = MediumFont,
                                        style = MaterialTheme.typography.labelSmall,
                                        color = Color.White.copy(alpha = 0.8f)
                                    )
                                    Text(
                                        text = "2",
                                        fontFamily = BoldFont,
                                        style = MaterialTheme.typography.titleLarge,
                                        color = Color.White
                                    )
                                }
                            }
                        }
                    }


                    Text(
                        "Apply for Loan",
                        style = MaterialTheme.typography.titleMedium,
                        fontFamily = BoldFont,
                        color = HeadingColor
                    )

                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Loan Amount") },
                        placeholder = { Text("Enter loan amount") },
                        prefix = { Text("₹ ") },
                        singleLine = true,
                        shape = RoundedCornerShape(14.dp)
                    )

                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Loan Date") },
                        placeholder = { Text("DD/MM/YYYY") },
                        singleLine = true,
                        shape = RoundedCornerShape(14.dp)
                    )

                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Return Date") },
                        placeholder = { Text("DD/MM/YYYY") },
                        singleLine = true,
                        shape = RoundedCornerShape(14.dp)
                    )

                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Additional Note (Optional)") },
                        placeholder = { Text("Write something...") },
                        minLines = 3,
                        shape = RoundedCornerShape(14.dp)
                    )

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = SurfaceColor
                        )
                    ) {
                        Row(
                            modifier = Modifier.padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Text(
                                "ⓘ", color = PrimaryColor, style = MaterialTheme.typography.titleLarge
                            )

                            Text(
                                "Your loan record will be submitted to the administrator for verification.",
                                color = PrimaryTextColor,
                                fontFamily = MediumFont,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }

                    Button(
                        onClick = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = PrimaryColor
                        )
                    ) {
                        Text(
                            "Submit Loan Record",
                            fontFamily = BoldFont,
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }
            }
        }
    }
}
