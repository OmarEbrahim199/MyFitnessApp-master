package com.example.myfitnessapp.di

import com.example.myfitnessapp.MyFitnessApp
import com.example.myfitnessapp.R
import com.example.myfitnessapp.data.network.RecipeService
import com.example.myfitnessapp.data.network.model.RecipeDtoMapper
import com.example.myfitnessapp.util.BASE_URL
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRecipeMapper(): RecipeDtoMapper {
        return RecipeDtoMapper()
    }

    @Singleton
    @Provides
    fun provideRecipeService(): RecipeService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(RecipeService::class.java)
    }

    @Singleton
    @Provides
    @Named("auth_token")
    fun provideAuthToken(): String {
        return MyFitnessApp.getContext().resources.getString(R.string.STATE_KEY_AUTH_TOKEN)
    }
}