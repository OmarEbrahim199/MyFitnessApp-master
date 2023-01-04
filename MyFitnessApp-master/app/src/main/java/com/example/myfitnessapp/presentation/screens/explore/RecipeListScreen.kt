package com.example.myfitnessapp.presentation.screens.explore

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NightlightRound
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.myfitnessapp.presentation.shared.components.recipes.RecipeList
import com.example.myfitnessapp.presentation.shared.components.recipes.SearchAppBar
import com.example.myfitnessapp.navigation.MainActions
import com.example.myfitnessapp.presentation.shared.viewmodel.RecipeListViewModel
import com.example.myfitnessapp.util.RecipeListEvent.*
import com.example.myfitnessapp.ui.theme.AppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun RecipeListScreen(navController: NavHostController, actions: MainActions, viewModel: RecipeListViewModel = hiltViewModel()) {

    val systemUiController = rememberSystemUiController()
    val systemBarColor = MaterialTheme.colors.primary

    SideEffect {
        systemUiController.setStatusBarColor(
            color = systemBarColor
        )
    }

    Scaffold(
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.primary,
        topBar = {

        },

        content = {

            val loading = viewModel.loading.value
            val scaffoldState = rememberScaffoldState()
            AppTheme(
                loading,
                scaffoldState,
                Alignment.BottomCenter
            ) {
                val recipes = viewModel.recipes.value
                val query = viewModel.query.value
                val page = viewModel.page.value
                val isDark = false // application.isDarkTheme.value

                Scaffold(
                    topBar = {
                        SearchAppBar(
                            query = query,
                            onQueryChanged = viewModel::onQueryChanged,
                            onExecuteSearch = {

                                viewModel.onTriggerEvent(SearchEvent)
                            },
                            categoryScrollPosition = viewModel.categoryScrollPosition(),
                            onCategoryScrollPositionChanged = viewModel::onCategoryScrollPositionChanged,
                            toggleThemeIcon = if (isDark) Icons.Filled.WbSunny else Icons.Filled.NightlightRound
                        ) {
                            //application.toggleTheme()
                        }
                    }, scaffoldState = scaffoldState,
                    snackbarHost = { scaffoldState.snackbarHostState }
                ) {
                    Column(
                        modifier = Modifier.background(MaterialTheme.colors.background)

                    ) {

                        RecipeList(
                            loading,
                            recipes,
                            viewModel::onRecipeScrollPositionChanged,
                            page,
                            { viewModel.onTriggerEvent(NextPageEvent) },
                            scaffoldState,
                            //navController,
                            actions,
                            isDark,

                        )

                    }

                }
            }
        }
    )
}
//snackbarController,
//  navController.navigate(route =BottomNavItem.Home.screen_route.toString()),
//   findNavController( navController.navigate(route = BottomNavItem.MovieDetails.passMovieId(1.toString())),
