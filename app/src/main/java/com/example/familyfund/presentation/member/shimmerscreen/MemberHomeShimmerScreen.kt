package com.example.familyfund.presentation.member.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.familyfund.presentation.admin.screen.BackgroundColor
import com.example.familyfund.presentation.shummer.shimmerEffect

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun MemberHomeShimmerScreen() {


    Scaffold(

        containerColor = BackgroundColor,

        topBar = {

            TopAppBar(

                title = {

                    Box(
                        modifier = Modifier
                            .height(20.dp)
                            .width(150.dp)
                            .shimmerEffect()
                    )

                }

            )

        },


        ) { innerPadding ->

        LazyColumn(

            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 12.dp),

            verticalArrangement = Arrangement.spacedBy(8.dp)

        ) {


            item {
                Spacer(modifier = Modifier.height(18.dp))

                OverviewCard()
            }

            item {


                Spacer(modifier = Modifier.height(18.dp))

                TextButton(

                    onClick = {

                    },


                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .shimmerEffect(),

                    shape = RoundedCornerShape(16.dp),


                    ) {


                }

                Spacer(modifier = Modifier.height(18.dp))

                Box(
                    modifier = Modifier
                        .height(20.dp)
                        .width(150.dp)
                        .shimmerEffect()
                )

                Spacer(modifier = Modifier.height(12.dp))

            }



            item {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    contentAlignment = Alignment.Center
                ) {

                    Box(
                        modifier = Modifier
                            .height(18.dp)
                            .width(250.dp)
                            .shimmerEffect()
                    )
                }
            }


        }


    }


}

@Composable
private fun OverviewCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
        , // Fixed height ke bajaye wrapContentHeight() bhi use kar sakte hain
        shape = RoundedCornerShape(22.dp), colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow // Soft background card ke liye
        ), elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                ,
            verticalArrangement = Arrangement.SpaceBetween // Content ko evenly distribute karne ke liye
        ) {
            // Top Section: Title & Main Amount/Value
            Column {
                // Subtitle / Label placeholder
                Box(
                    modifier = Modifier
                        .height(14.dp)
                        .width(140.dp)
                        .clip(RoundedCornerShape(8.dp)) // Rounded edges for smooth look
                        .shimmerEffect()
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Main Big Value placeholder
                Box(
                    modifier = Modifier
                        .height(28.dp)
                        .width(190.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .shimmerEffect()
                )
            }

            // Middle Divider (Ispar shimmer hatakar subtle alpha color rakhein)
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f)
            )

            // Bottom Section: Two Columns (Income/Expense ya Left/Right stats)
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Left Stat
                Column {
                    Box(
                        modifier = Modifier
                            .height(12.dp)
                            .width(80.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .shimmerEffect()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .height(18.dp)
                            .width(110.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .shimmerEffect()
                    )
                }

                // Right Stat (Alignment ke liye End align bhi kar sakte hain)
                Column(horizontalAlignment = Alignment.End) {
                    Box(
                        modifier = Modifier
                            .height(12.dp)
                            .width(80.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .shimmerEffect()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .height(18.dp)
                            .width(110.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .shimmerEffect()
                    )
                }
            }
        }
    }
}



