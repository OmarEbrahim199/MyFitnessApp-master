package com.example.myfitnessapp.util

sealed class RecipeEvent {
    data class GetRecipeEvent(val id: Int) : RecipeEvent()
}
