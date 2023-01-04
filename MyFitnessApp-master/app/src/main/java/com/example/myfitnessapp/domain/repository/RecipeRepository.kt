package com.example.myfitnessapp.domain.repository

import com.example.myfitnessapp.domain.models.Recipe

interface RecipeRepository {
    suspend fun search(token: String, page: Int, query: String): List<Recipe>
    suspend fun getRecipe(token: String, id: Int): Recipe
}