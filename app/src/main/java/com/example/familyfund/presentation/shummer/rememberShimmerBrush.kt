package com.example.familyfund.presentation.shummer

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun rememberShimmerBrush(

    targetValue:Float = 1000f,
    showShimmer:Boolean = true,
    durationMillis:Int = 1200
): Brush{


    val shimmerColor = if(showShimmer){

        listOf(
            Color(0xFFE0E0E0).copy(0.9f),
            Color(0xFFF5F5F5).copy(0.2f),
            Color(0xFFE0E0E0)
        )
    }else {

        listOf(Color.Transparent,Color.Transparent,Color.Transparent)
    }


    val transition = rememberInfiniteTransition(label = "shimmer")
    val translateAnimation by  transition.animateFloat(

        initialValue = 0f,
        targetValue = targetValue,
        animationSpec = infiniteRepeatable(
            animation =  tween(durationMillis = durationMillis, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmer_translate"
    )


    return Brush.linearGradient(

        colors = shimmerColor,
        start = Offset(x=translateAnimation-targetValue, y = 0f),
        end = Offset(x = translateAnimation, y = 0f)
    )


}