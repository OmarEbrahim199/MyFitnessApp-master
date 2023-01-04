package com.example.myfitnessapp.domain.repository

import androidx.lifecycle.MutableLiveData
import com.example.myfitnessapp.domain.models.*
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.example.myfitnessapp.util.Response

interface WorkoutRepository {


    suspend fun addWorkoutPlan(
        workoutPlan: WorkoutPlan,
        workouts: ArrayList<Workout>,
        uid:String
    ): Response<Void>

    suspend fun getWorkoutPlan(uid:String): Response<QuerySnapshot>

    suspend fun addWorkouts(uid:String): Response<Void>

    suspend fun getWorkouts(uid:String): Response<QuerySnapshot>

    suspend fun getExercises(uid:String):MutableLiveData<Response<QuerySnapshot>>

    suspend fun getUser(uid:String): Response<DocumentSnapshot>

    suspend fun addExerciseToWorkout(exerciseItem: ExerciseItem, workoutId:String, uid:String): Response<Void>
    suspend fun updateWorkout(workoutId: String,
                              exerciseItem: ExerciseItem? = null,
                              volume: ExerciseVolume? = null,
                              workout: Workout,
                              uid:String
    ): Response<Void>

    suspend fun addNewExercise(exercise: Exercise, uid:String): Response<Void>

    suspend fun addExerciseHistory(exercise: ExerciseHistoryItem, uid:String)

    suspend fun getHistoryData(uid:String): Response<QuerySnapshot>

    suspend fun getHistoryDataDetails(exerciseId: String,uid:String): Response<QuerySnapshot>
    suspend fun deleteWorkoutPlan(workoutPlanId: String,uid:String)




}