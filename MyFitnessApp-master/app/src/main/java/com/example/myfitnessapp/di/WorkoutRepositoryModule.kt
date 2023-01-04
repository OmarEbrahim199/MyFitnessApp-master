package com.example.myfitnessapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.example.myfitnessapp.data.repository.WorkoutRepositoryImpl
import com.example.myfitnessapp.domain.repository.WorkoutRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class WorkoutRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindWorkoutRepository (workoutRepositoryImpl: WorkoutRepositoryImpl): WorkoutRepository
}