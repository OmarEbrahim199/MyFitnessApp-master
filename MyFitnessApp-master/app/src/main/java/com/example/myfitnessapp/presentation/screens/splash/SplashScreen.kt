package com.example.myfitnessapp.presentation.screens.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myfitnessapp.presentation.shared.components.animation.LottieAsset
import com.example.myfitnessapp.navigation.Screens
import com.example.myfitnessapp.presentation.shared.viewmodel.UserViewModel
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(
    navController: NavHostController = rememberNavController(),
    userViewModel: UserViewModel,
)  {

    LaunchedEffect(key1 = userViewModel?.hasUser){
        delay(1000L)
        if (userViewModel.hasUser){
            navController.navigate(Screens.Home.route)
        }else {
            navController.navigate(Screens.Login.route)
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAsset(asset = "wait.json")

    }
}
