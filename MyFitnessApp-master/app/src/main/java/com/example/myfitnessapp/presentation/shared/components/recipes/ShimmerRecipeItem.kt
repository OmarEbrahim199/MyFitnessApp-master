package com.example.myfitnessapp.presentation.shared.components.recipes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerRecipeItem(
    colors: List<Color>,
    cardHeight: Dp,
    padding: Dp,
    xTranslation: Float,
    yTranslation: Float,
    gradientWidth: Float
) {
    val brush = Brush.linearGradient(
        colors,
        start = Offset(xTranslation - gradientWidth / 2, yTranslation - gradientWidth / 2),
        end = Offset(xTranslation + gradientWidth / 2, yTranslation + gradientWidth / 2)
    )

    Column(modifier = Modifier.padding(padding)) {
        Surface(shape = MaterialTheme.shapes.medium) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(cardHeight)
                    .background(brush = brush)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Surface(shape = MaterialTheme.shapes.medium) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .height(24.dp)
                        .padding(vertical = 8.dp)
                        .background(brush = brush)
                )
            }
            Surface(shape = MaterialTheme.shapes.medium) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(18.dp)
                        .padding(8.dp)
                        .align(Alignment.CenterVertically)
                        .background(brush = brush)
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Surface(shape = MaterialTheme.shapes.medium) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
                    .padding(vertical = 8.dp)
                    .background(brush = brush)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Surface(shape = MaterialTheme.shapes.medium) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
                    .padding(vertical = 8.dp)
                    .background(brush = brush)
            )
        }
    }
}