package com.example.aibatteryusagebenchmark.ui

import android.app.Application
import com.example.aibatteryusagebenchmark.data.DataModule
import com.example.aibatteryusagebenchmark.domain.DomainModule
import com.example.aibatteryusagebenchmark.presentation.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module


class LandmarkClassifierApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@LandmarkClassifierApp)
            modules(
                DataModule().module,
                DomainModule().module,
                PresentationModule().module,
                AppModule().module
            )
        }
    }
}