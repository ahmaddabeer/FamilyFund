package com.example.familyfund.presentation.shummer

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

fun Modifier.shimmerEffect(
    showShimmer: Boolean = true,
    shape: Shape = RoundedCornerShape(8.dp)
): Modifier = composed {

    val brush = rememberShimmerBrush(showShimmer= showShimmer)
    if(showShimmer){

        this.background(brush=brush,shape=shape)

    }else {
        this
    }
}