package com.example.myfitnessapp.domain.repository

import com.google.firebase.auth.AuthResult
import com.example.myfitnessapp.util.Response

interface UserRepository {

    suspend fun createNewUser(
        userName: String,
        userEmailAddress: String,
        userLoginPassword: String
    ): Response<AuthResult>

    fun hasUser(): Boolean
    suspend fun loginUser(email: String, password: String): Response<AuthResult>

    suspend fun logOutUser()
}