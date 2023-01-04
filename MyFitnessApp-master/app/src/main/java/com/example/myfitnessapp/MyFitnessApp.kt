package com.example.myfitnessapp

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MyFitnessApp : Application(){

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var context: Context? = null
        fun getContext(): Context {
            return context!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}