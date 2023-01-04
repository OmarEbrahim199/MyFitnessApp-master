package com.example.myfitnessapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myfitnessapp.navigation.RootNavGraph
import com.example.myfitnessapp.presentation.shared.viewmodel.UserViewModel
import com.example.myfitnessapp.presentation.shared.viewmodel.WorkoutViewModel
import com.example.myfitnessapp.ui.theme.MyFitnessAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            MyFitnessAppTheme {
                val workoutViewModel = hiltViewModel<WorkoutViewModel>()
                val userViewModel = hiltViewModel<UserViewModel>()
                navController = rememberNavController()
                RootNavGraph(navController,userViewModel,workoutViewModel)

            }
        }
    }
}




