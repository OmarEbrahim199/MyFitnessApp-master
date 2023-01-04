package com.example.myfitnessapp.presentation.shared.components.recipes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myfitnessapp.util.STATE_KEY_RECIPE_DETAILS

@Composable
fun RecipeDetailsTopBar(
    navController: NavController
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.onSurface,
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back Icon",
                    tint = MaterialTheme.colors.primarySurface
                )
            }
        },
        title = {
            Text(
                text = (STATE_KEY_RECIPE_DETAILS),
                color = MaterialTheme.colors.background,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.h6
            )
        },
        elevation = 0.dp,
    )
}