package com.example.myfitnessapp.presentation.shared.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfitnessapp.domain.models.User
import com.example.myfitnessapp.data.states.AuthState
import com.example.myfitnessapp.domain.repository.UserRepository
import com.example.myfitnessapp.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(
    private val app: Application,
    private val repository: UserRepository
) : ViewModel() {

    val hasUser:Boolean
        get() = repository.hasUser()


    var signUpState by mutableStateOf(AuthState())
        private set

    var signInState by mutableStateOf(AuthState())
        private set

    var user by mutableStateOf(User())
        private set

    fun signUpUser(
        userName: String,
        userEmail: String,
        userPassword: String,
        confirmPassword: String
    ) =
        viewModelScope.launch {

            val arePasswordMatching = userPassword == confirmPassword

            if (arePasswordMatching) {

                signUpState = signUpState.copy(
                    loading = true,
                    error = null,
                    success = false
                )

                val result = repository.createNewUser(
                    userName = userName,
                    userEmailAddress = userEmail,
                    userLoginPassword = userPassword
                )

                when (result) {
                    is Response.Success -> {
                        signUpState = signUpState.copy(
                            data = result.data,
                            loading = false,
                            success = true,
                            error = null
                        )
                    }

                    is Response.Error -> {
                        signUpState = signUpState.copy(
                            data = null,
                            loading = false,
                            success = false,
                            error = result.message.toString()
                        )
                    }

                    is Response.Loading -> {
                        signUpState = signUpState.copy(
                            data = null,
                            loading = true,
                            success = false,
                            error = null
                        )
                    }
                }
            } else {
                signUpState = signUpState.copy(
                    error = "passwords are not matching"
                )
            }

        }

    fun signInUser(userEmail: String, userPassword: String) =

        viewModelScope.launch {

            signInState = signInState.copy(
                loading = true
            )

            val result = repository.loginUser(email = userEmail, password = userPassword)

            signInState = when (result) {
                is Response.Success -> {
                    signInState.copy(
                        loading = false,
                        success = true,
                        uid = result.data?.user?.uid
                    )

                }

                is Response.Loading -> {
                    signInState.copy(
                        loading = true,
                        success = false,
                    )
                }

                is Response.Error -> {
                    signInState.copy(
                        loading = false,
                        success = false,
                        error = result.message
                    )
                }
            }
        }

    fun logOut() = viewModelScope.launch {

        repository.logOutUser()

        signInState = AuthState()
        signUpState = AuthState()

    }


}