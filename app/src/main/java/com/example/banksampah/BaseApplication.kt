package com.example.banksampah

import android.app.Application
import com.example.banksampah.utill.Session
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Session.init(this)
    }
}