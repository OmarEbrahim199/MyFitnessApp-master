package com.example.myfitnessapp.navigation

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.MutableState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.myfitnessapp.presentation.screens.login.LoginScreen
import com.example.myfitnessapp.presentation.screens.signup.SignUpScreen
import com.example.myfitnessapp.presentation.screens.splash.SplashScreen
import com.example.myfitnessapp.presentation.shared.viewmodel.UserViewModel
import com.example.myfitnessapp.presentation.shared.viewmodel.WorkoutViewModel


fun NavGraphBuilder.loginNavGraph(
    navController: NavHostController,
    bottomBarState: MutableState<Boolean>,
    userViewModel: UserViewModel,
    workoutViewModel: WorkoutViewModel,
    scaffoldState: ScaffoldState
) {

    navigation(startDestination = Screens.SplashScreen.route, route = LOGIN_ROUTE)
    {
        composable(route = Screens.SplashScreen.route){
            SplashScreen(navController,userViewModel)
            //LoginScreen(navController,userViewModel,scaffoldState)
            bottomBarState.value = false
        }
        composable(route = Screens.Login.route){
            LoginScreen(navController,userViewModel,scaffoldState)
            bottomBarState.value = false
        }
        composable(route = Screens.Signup.route){
            SignUpScreen(navController,userViewModel,scaffoldState)
            bottomBarState.value = false
        }
    }
}