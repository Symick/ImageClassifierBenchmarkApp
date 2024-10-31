package com.example.aibatteryusagebenchmark.data

import android.graphics.Bitmap
import com.example.aibatteryusagebenchmark.domain.data.LandmarkClassifier
import com.example.aibatteryusagebenchmark.domain.model.Classification
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Named

@Factory
@Named("NoOp")
class NoOpLandmarkClassifier : LandmarkClassifier {

    override suspend fun classify(bitmap: Bitmap): List<Classification> {
        return listOf(
            Classification(
                "",
                0.9f
            )
        )
    }
}