package com.example.familyfund.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.familyfund.R
import com.example.familyfund.presentation.component.TransactionCard
import dev.seyfarth.tablericons.TablerIcons
import dev.seyfarth.tablericons.outlined.Menu
import dev.seyfarth.tablericons.outlined.UserCircle


val PoppinsBold = FontFamily(
    Font(R.font.popin_bold, FontWeight.Bold)
)

val PopinsMedium = FontFamily(
    Font(R.font.medium, FontWeight.Medium)
)

val PrimaryBlue = Color(0xFF2563EB)
val NavyBlue = Color(0xFF172554)
val LightBlue = Color(0xFFEFF6FF)
val Background = Color(0xFFF8FAFC)
val DarkText = Color(0xFF1E293B)
val GreyText = Color(0xFF64748B)


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun HomeScreen() {


    Scaffold(

        topBar = {

            CenterAlignedTopAppBar(

                title = {

                    Text(
                        "FamilyFund",
                        style = MaterialTheme.typography.headlineSmall,
                        fontFamily = PopinsMedium
                    )

                },
                navigationIcon = {

                    IconButton(onClick = {}) {

                        Icon(imageVector = TablerIcons.Outlined.Menu, contentDescription = "Menu")
                    }
                },
                actions = {


                    IconButton(onClick = {}) {

                        Icon(
                            imageVector = TablerIcons.Outlined.UserCircle,
                            contentDescription = "Menu"
                        )

                    }

                }
            )

        }
    ) { innerPadding ->


        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {


            item {

                OverViewCard()

                Spacer(modifier = Modifier.height(15.dp).background(color = LightBlue))


                TextButton(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(

                        containerColor = PrimaryBlue
                    )
                ) {

                    Text("Varification Pending", fontFamily = PopinsMedium)
                }


                Spacer(modifier = Modifier.height(10.dp))
                Text("Dec Transaction",style = MaterialTheme.typography.titleSmall, fontFamily = PoppinsBold)








            }

            items(10){

                TransactionCard()
            }

        }


    }
}


@Composable
private fun OverViewCard() {

    Card(
        modifier = Modifier.fillMaxSize(),
        colors = CardDefaults.cardColors(

            containerColor = PrimaryBlue,
            contentColor = LightBlue

        )

    ) {

        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Text(
                "Total Balance",
                style = MaterialTheme.typography.titleMedium,
                fontFamily = PopinsMedium
            )
            Text("₹ 1250.0", fontFamily = PoppinsBold, style = MaterialTheme.typography.titleLarge)


            HorizontalDivider(modifier = Modifier.fillMaxWidth(), color = LightBlue)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {


                Column(modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "Paid (This Month)",
                        fontFamily = PopinsMedium,
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(text = "6 Members", style = MaterialTheme.typography.bodySmall, fontFamily = PopinsMedium)
                }

                Column(modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "Pending",
                        fontFamily = PopinsMedium,
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(text = "4 Members", style = MaterialTheme.typography.bodySmall, fontFamily = PopinsMedium)
                }


            }


        }


    }
}