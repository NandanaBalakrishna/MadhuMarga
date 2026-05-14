package com.example.madhumargaa

import android.app.Application
import com.google.firebase.FirebaseApp

class MadhuApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize Firebase services for the entire app
        FirebaseApp.initializeApp(this)
    }
}