package com.example.animal_crossing.ui.customComposables

import androidx.compose.animation.core.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun PulsatingHeart(infiniteTransition: InfiniteTransition): Dp {
    val pulsate by infiniteTransition.animateFloat(
        initialValue = 30f,
        targetValue = 40f,
        animationSpec = infiniteRepeatable(tween(800), repeatMode = RepeatMode.Reverse)
    )
    return with(LocalDensity.current) {
        pulsate.dp
    }
}