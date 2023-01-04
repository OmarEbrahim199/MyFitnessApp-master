package com.example.myfitnessapp.util

sealed class RecipeListEvent {
    object SearchEvent : RecipeListEvent()
    object NextPageEvent : RecipeListEvent()

    //restore after process death
    object RestoreEvent : RecipeListEvent()
}