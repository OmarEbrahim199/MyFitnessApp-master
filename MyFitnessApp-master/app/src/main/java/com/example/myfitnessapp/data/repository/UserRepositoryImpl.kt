package com.example.myfitnessapp.data.repository

import android.app.Application
import android.util.Log
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.example.myfitnessapp.domain.models.User
import com.example.myfitnessapp.domain.repository.UserRepository
import com.example.myfitnessapp.util.Response
import com.google.firebase.auth.ktx.auth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val app: Application,
    private val auth: FirebaseAuth
) : UserRepository {

    private val fireStoreUserCollection = Firebase.firestore.collection("users")

    val currentUser = auth.currentUser

    override fun hasUser():Boolean = Firebase.auth.currentUser != null

    override suspend fun createNewUser(
        userName: String,
        userEmailAddress: String,
        userLoginPassword: String
    ): Response<AuthResult> {

        return try {

            val registrationResult =
                auth.createUserWithEmailAndPassword(userEmailAddress, userLoginPassword)
                    .await()

            val userId = registrationResult.user?.uid!!
            val newUser = User(
                userName = userName,
                userEmail = userEmailAddress
            )
            fireStoreUserCollection.document(userId).set(newUser).await()

            Response.Success(registrationResult)

        } catch (e: Exception) {
            e.printStackTrace()
            Response.Error(e.message.toString())
        }


    }

    override suspend fun loginUser(email: String, password: String): Response<AuthResult> {

        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            Log.e("login", "logged in user ${result.user?.uid}")
            Response.Success(result)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Error(e.message.toString())
        }


    }

    override suspend fun logOutUser() {
        auth.signOut()
    }


}