package com.example.myfitnessapp.data.network.model

import com.example.myfitnessapp.domain.models.Recipe
import com.example.myfitnessapp.util.DomainMapper

// map our network entity to our domain and back,
// because they are tow different models so if we get data from the network we need to be able to map it to our domain model
// if we have a domain model we need to be able to map it to a network model
// after that we work on the actual retrofit interface for getting the data from the network
class RecipeDtoMapper : DomainMapper<RecipeDto, Recipe> {

    override fun mapToDomainModel(model: RecipeDto): Recipe {
        return with(model) {
            return@with Recipe(
                id,
                title,
                publisher,
                featuredImage,
                rating,
                sourceUrl,
                description,
                cookingInstructions,
                ingredients ?: listOf(),
                dateAdded,
                dateUpdated
            )
        }
    }



    fun toDomainList(initial: List<RecipeDto>): List<Recipe> {
        return initial.map {
            mapToDomainModel(it)
        }
    }


}