package com.example.myfitnessapp.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize



/*
@Parcelize is an annotation provided by Kotlin Android Extensions
that will automatically generate the serialization implementation
for your custom Parcelable type at compile time
 */
@Parcelize
data class Recipe(
    val id: Int? = null,
    val title: String? = null,
    val publisher: String? = null,
    val featuredImage: String? = null,
    val rating: Int? = null,
    val sourceUrl: String? = null,
    val description: String? = null,
    val cookingInstructions: List<String>? = null,
    val ingredients: List<String> = listOf(),
    val dateAdded: String? = null,
    val dateUpdated: String? = null
): Parcelable