package com.example.myfitnessapp.presentation.shared.components.recipes

import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.myfitnessapp.domain.models.Recipe
import com.example.myfitnessapp.navigation.MainActions
import com.example.myfitnessapp.util.RecipeListEvent
import com.example.myfitnessapp.util.PAGE_SIZE


private const val TAG = "RecipeList"

@Composable
fun RecipeList(
    loading: Boolean,
    recipes: List<Recipe>,
    onRecipeScrollPositionChanged: (Int) -> Unit,
    page: Int,
    onNextPage: (RecipeListEvent) -> Unit,
    scaffoldState: ScaffoldState,
    actions: MainActions,
    isDark: Boolean
) {
    Box(
        modifier = Modifier.fillMaxSize()

    ) {


        /*if (loading && recipes.isEmpty()) ShimmerRecipeCardAnimation(
            RECIPE_CARD_HEIGHT
        )*/

        LazyColumn {
            itemsIndexed(
                items = recipes
            ) { index, recipe ->
                onRecipeScrollPositionChanged(index)
                if ((index + 1) >= (page * PAGE_SIZE) && !loading) {
                    onNextPage(RecipeListEvent.NextPageEvent)
                }
                recipeCard(recipe) {
                    Log.d(TAG, "RecipeList: CLICKED")
                    if (recipe.id != null) {
                        val bundle = Bundle()
                        bundle.putInt("recipeId", recipe.id)

                        bundle.putBoolean("isDark", isDark)
                       // navController.navigate(recipe.id, bundle)
                        //navController.navigate(route = BottomNavItem.MovieDetails.screen_route)
                        actions.gotoRecipeDetails.invoke(recipe.id.toString())
                    }
                }
            }
        }

    }
}