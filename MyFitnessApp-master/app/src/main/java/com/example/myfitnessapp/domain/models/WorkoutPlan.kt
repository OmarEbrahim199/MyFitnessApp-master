package com.example.myfitnessapp.domain.models

import java.time.DayOfWeek

data class WorkoutPlan (
    val name:String? = null,
    val workouts:ArrayList<DayOfWeek>? = null,
    val duration:Int? = null,
)
