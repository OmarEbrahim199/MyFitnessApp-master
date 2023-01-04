package com.example.myfitnessapp.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myfitnessapp.presentation.shared.viewmodel.UserViewModel
import com.example.myfitnessapp.presentation.shared.viewmodel.WorkoutViewModel
import com.example.myfitnessapp.ui.theme.myDarkBlue


@Composable
fun RootNavGraph(
    navController: NavHostController,
    userViewModel: UserViewModel = viewModel(),
    workoutViewModel: WorkoutViewModel = viewModel(),

) {


    val actions = remember(navController) { MainActions(navController) }

    val bottomBarState = rememberSaveable { (mutableStateOf(false)) }
    val scaffoldState = rememberScaffoldState()



    val navBackStackEntry by navController.currentBackStackEntryAsState()

    when (navBackStackEntry?.destination?.route) {
        LOGIN_ROUTE -> bottomBarState.value = false
        MAIN_ROUTE -> bottomBarState.value = true
    }

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            when (bottomBarState.value) {
                true -> BottomNavBar(navController = navController, bottomBarState)
                false -> {}
            }
        },
        backgroundColor = myDarkBlue,

        ) {

        NavHost(
            navController = navController,
            startDestination = LOGIN_ROUTE,
            route = ROOT_ROUTE,
            modifier = Modifier.padding(it)
        )
        {

            loginNavGraph(
                navController = navController,
                bottomBarState,
                userViewModel,
                workoutViewModel,
                scaffoldState
            )

            mainNavGraph(
                navController = navController,
                bottomBarState,
                userViewModel,
                workoutViewModel,
                scaffoldState,
                actions
            )

        }
    }

}


class MainActions(navController: NavController) {

    val upPress: () -> Unit = {
        navController.navigateUp()
    }

    val gotoRecipeDetails: (String) -> Unit = { isbnNo ->
        navController.navigate("${Screens.MovieDetails.route}/$isbnNo")
    }


}
