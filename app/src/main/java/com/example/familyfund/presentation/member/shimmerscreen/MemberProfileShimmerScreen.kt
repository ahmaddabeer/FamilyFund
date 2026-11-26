package com.example.familyfund.presentation.member.shimmerscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.familyfund.presentation.admin.screen.BackgroundColor
import com.example.familyfund.presentation.admin.screen.SurfaceColor
import com.example.familyfund.presentation.shummer.shimmerEffect

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun MemberProfileShimmerScreen() {


    Scaffold(
        containerColor = BackgroundColor,
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier
                            .height(20.dp)
                            .width(120.dp)
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
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            item {

                Spacer(modifier = Modifier.height(20.dp))

                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .shimmerEffect()
                )



                Spacer(modifier = Modifier.height(14.dp))

                Box(
                    modifier = Modifier
                        .height(20.dp)
                        .width(150.dp)
                        .shimmerEffect()
                )

                Spacer(modifier = Modifier.height(14.dp))

                Box(
                    modifier = Modifier
                        .height(15.dp)
                        .width(100.dp)
                        .shimmerEffect()
                )

                Spacer(modifier = Modifier.height(28.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(18.dp),
                        verticalArrangement = Arrangement.spacedBy(18.dp)
                    ) {
                        ProfileDetail()
                        HorizontalDivider(color = SurfaceColor)
                        ProfileDetail()
                        HorizontalDivider(color = SurfaceColor)
                        ProfileDetail()
                        HorizontalDivider(color = SurfaceColor)
                        ProfileDetail()
                        HorizontalDivider(color = SurfaceColor)
                        ProfileDetail()
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Box(

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .shimmerEffect()
                        .background(
                            color = Color.White, shape = RoundedCornerShape(12.dp)
                        ),
                )





            }
        }
    }


}


@Composable
private fun ProfileDetail(

) {
    Column(
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Box(
            modifier = Modifier
                .height(10.dp)
                .width(100.dp)
                .shimmerEffect()
        )

        Box(
            modifier = Modifier
                .height(15.dp)
                .width(200.dp)
                .shimmerEffect()
        )
    }
}