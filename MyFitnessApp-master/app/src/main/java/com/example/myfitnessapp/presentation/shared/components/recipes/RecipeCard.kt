package com.example.myfitnessapp.presentation.shared.components.recipes

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.myfitnessapp.domain.models.Recipe
import com.example.myfitnessapp.presentation.shared.components.rating.components.RatingComponent
import com.example.myfitnessapp.util.RECIPE_CARD_HEIGHT


private const val TAG = "RecipeCard"

@Composable
fun recipeCard(
    recipe: Recipe,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier.clickable(onClick = onClick)
        ) {
            recipe.featuredImage?.let {
                FoodImage(url = it, height = RECIPE_CARD_HEIGHT)
            }
            recipe.title?.let {
                Row(
                    modifier = Modifier.padding(
                        vertical = 4.dp,
                        horizontal = 8.dp
                    )
                ) {
                    Text(
                        text = it,
                        modifier = Modifier
                            .fillMaxWidth(0.805f)
                            .wrapContentWidth(Alignment.Start),
                        style = MaterialTheme.typography.h3,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    recipe.rating?.toString()?.let { RatingComponent(rating = it)
                        Log.e("RatingComponent",it)

                    }
                   /* Text(
                        text = recipe.rating.toString(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.End)
                            .padding(2.dp)
                            .align(Alignment.CenterVertically),
                        style = MaterialTheme.typography.h4
                    )*/
                }
            }
        }
    }
}