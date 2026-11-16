package com.example.familyfund.presentation.admin.screen

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.familyfund.R
import com.example.familyfund.presentation.admin.component.BottomNavigationBar
import com.example.familyfund.presentation.admin.component.TransactionCard

val BoldFont = FontFamily(
    Font(R.font.popin_bold, FontWeight.Bold)
)

val MediumFont = FontFamily(
    Font(R.font.medium, FontWeight.Medium)
)

val PrimaryColor = Color(0xFF3B82F6)
val HeadingColor = Color(0xFF1E3A5F)
val SurfaceColor = Color(0xFFEFF6FF)
val BackgroundColor = Color(0xFFF8FAFC)
val PrimaryTextColor = Color(0xFF334155)
val SecondaryTextColor = Color(0xFF94A3B8)


@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun HomeScreen(controller: NavHostController) {

    val showNotificationDialog = remember {
        mutableStateOf(false)
    }

    val notificationButtonText = remember {
        mutableStateOf("Notify Administrator")
    }

    val monthlyContributionsList by remember { mutableStateOf(false)


    }

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

            BottomNavigationBar(controller)
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

                OverviewCard()

                Spacer(modifier = Modifier.height(18.dp))

                TextButton(

                    onClick = {
                        showNotificationDialog.value = true
                    },

                    enabled = notificationButtonText.value == "Notify Administrator",

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),

                    shape = RoundedCornerShape(14.dp),

                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryColor
                    )

                ) {

                    Text(
                        notificationButtonText.value,
                        fontFamily = MediumFont,
                        style = MaterialTheme.typography.labelLarge
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

            if(monthlyContributionsList){

                items(3) {

                    TransactionCard()

                }
            }else {

               item {

                   Box(modifier = Modifier.fillMaxWidth().height(150.dp), contentAlignment = Alignment.Center){

                       Text("No Transaction Available", fontFamily = MediumFont, color = SecondaryTextColor)
                   }
               }
            }

        }

    }

    if (showNotificationDialog.value) {

        NotifyPushScreen(
            { showNotificationDialog.value = false },
            { notificationButtonText.value = "Verification Pending" },
            isOpen = true
        )

    }

}


@Composable
private fun OverviewCard() {

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
                        text = "6 Members",
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
                        text = "4 Members",
                        style = MaterialTheme.typography.titleMedium,
                        fontFamily = BoldFont,
                        color = Color.White
                    )

                }

            }

        }

    }

}
