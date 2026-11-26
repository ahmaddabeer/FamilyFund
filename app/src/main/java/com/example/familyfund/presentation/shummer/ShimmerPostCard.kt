package com.example.familyfund.presentation.shummer

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerLoadingScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        // Top Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .shimmerEffect(shape = RoundedCornerShape(10.dp))
            )

            Box(
                modifier = Modifier
                    .size(36.dp)
                    .shimmerEffect(shape = CircleShape)
            )
        }

        // Heading
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.55f)
                    .height(22.dp)
                    .shimmerEffect()
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(13.dp)
                    .shimmerEffect()
            )
        }

        // Two Cards
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {

            repeat(2) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(90.dp)
                        .shimmerEffect(shape = RoundedCornerShape(12.dp))
                )
            }
        }

        // Content
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {

            repeat(3) { index ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth(
                            when (index) {
                                1 -> 0.85f
                                2 -> 0.65f
                                else -> 1f
                            }
                        )
                        .height(15.dp)
                        .shimmerEffect()
                )
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        // List Items
        repeat(3) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .shimmerEffect(shape = CircleShape)
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.6f)
                            .height(14.dp)
                            .shimmerEffect()
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.4f)
                            .height(12.dp)
                            .shimmerEffect()
                    )
                }
            }
        }
    }
}