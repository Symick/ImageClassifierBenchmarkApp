package com.example.aibatteryusagebenchmark.ui

import android.app.Application
import org.koin.core.context.startKoin



class LandmarkClassifierApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
        }
    }
}