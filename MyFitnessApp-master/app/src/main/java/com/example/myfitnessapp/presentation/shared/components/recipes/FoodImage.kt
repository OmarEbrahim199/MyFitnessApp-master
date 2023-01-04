package com.example.myfitnessapp.presentation.shared.components.recipes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import com.example.myfitnessapp.R
import com.google.accompanist.coil.rememberCoilPainter

@Composable
fun FoodImage(
    url: String,
    height: Dp
) {
    val painter = rememberCoilPainter(
        request = url,
        requestBuilder = {
            placeholder(R.drawable.ic_add)
        },
        fadeIn = true
    )
    Image(
        painter = painter,
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(height),
        contentScale = ContentScale.Crop
    )
}