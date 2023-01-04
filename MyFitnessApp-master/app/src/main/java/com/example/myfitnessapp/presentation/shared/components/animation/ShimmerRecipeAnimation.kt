package com.example.myfitnessapp.presentation.shared.components.animation

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.myfitnessapp.presentation.shared.components.recipes.ShimmerRecipeItem


@Composable
fun ShimmerRecipeAnimation(
    imageHeight: Dp,
    padding: Dp = 100.dp
) {

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        with(LocalDensity.current) {
            val cardWidthPx = (maxWidth - (padding * 2)).toPx()
            val cardHeightPx = (imageHeight - padding).toPx()
            val gradientWidth = (0.2f * cardHeightPx)

            val infiniteTransition = rememberInfiniteTransition()

            val xTransition by infiniteTransition.animateFloat(
                initialValue = -gradientWidth,
                targetValue = cardWidthPx + gradientWidth,
                animationSpec = infiniteRepeatable(
                    tween(durationMillis = 1000, easing = LinearOutSlowInEasing, delayMillis = 100),
                    RepeatMode.Restart
                )
            )
            val yTransition by infiniteTransition.animateFloat(
                initialValue = -gradientWidth,
                targetValue = cardHeightPx + gradientWidth,
                animationSpec = infiniteRepeatable(
                    tween(durationMillis = 1000, easing = FastOutSlowInEasing, delayMillis = 100),
                    RepeatMode.Restart
                )
            )

            ShimmerRecipeItem(
                colors = shimmerColors,
                cardHeight = imageHeight,
                padding = padding,
                xTranslation = xTransition,
                yTranslation = yTransition,
                gradientWidth = gradientWidth
            )


        }
    }
}