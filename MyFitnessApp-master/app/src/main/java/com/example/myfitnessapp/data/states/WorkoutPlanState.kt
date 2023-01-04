package com.example.myfitnessapp.data.states

import com.example.myfitnessapp.domain.models.WorkoutPlan

data class WorkoutPlanState(
    val workoutPlan: WorkoutPlan? = null,
    val loading:Boolean = false,
    val error:String? = null
)
