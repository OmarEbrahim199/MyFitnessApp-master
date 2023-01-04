package com.example.myfitnessapp.presentation.shared.components.recipes

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.More
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.myfitnessapp.domain.models.Recipe
import com.example.myfitnessapp.presentation.shared.components.rating.components.RatingComponent
import com.example.myfitnessapp.util.RECIPE_VIEW_HEIGHT


@Composable
fun RecipeView(
    recipe: Recipe
) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
    ) {
        recipe.featuredImage?.let {
            FoodImage(url = it, height = RECIPE_VIEW_HEIGHT)
        }
        
        IconButton( onClick = {
            showMessage(context = context,recipe.sourceUrl.toString())
        }, modifier = Modifier.padding(2.dp)) {
            Icon(imageVector = Icons.Filled.More, contentDescription = null)
            Text(text = "Read more")
        }
        
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                recipe.title?.let {
                    Text(
                        text = it,
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .wrapContentWidth(Alignment.Start),
                        style = MaterialTheme.typography.h3
                    )
                }
                recipe.rating?.toString()?.let { RatingComponent(rating = it)
                    Log.e("RatingComponent",it)

                }



            }
            recipe.publisher?.let {
                Text(
                    text = recipe.dateUpdated?.let { updated ->
                        "Updated on $updated by $it"
                    } ?: recipe.dateAdded?.let { added ->
                        "Added on $added by $it"
                    } ?: "By $it",
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .padding(4.dp)
                        .wrapContentWidth(Alignment.Start),
                    style = MaterialTheme.typography.caption
                )
            }
            recipe.ingredients.forEach {
                Text(
                    text = it,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    style = MaterialTheme.typography.body1
                )
            }

        }
    }
}

fun showMessage(context: Context, string: String) {
    val browserIntent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse(string)
    )
    ContextCompat.startActivity(context, browserIntent, null)
}
