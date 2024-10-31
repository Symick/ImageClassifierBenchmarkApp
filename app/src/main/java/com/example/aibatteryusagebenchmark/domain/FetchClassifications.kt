package com.example.aibatteryusagebenchmark.domain

import android.graphics.Bitmap
import com.example.aibatteryusagebenchmark.domain.data.LandmarkClassifier
import com.example.aibatteryusagebenchmark.domain.model.Classification
import org.koin.core.annotation.Factory

@Factory
class FetchClassifications(private val landmarkClassifier: LandmarkClassifier) {

    operator fun invoke(bitmap: Bitmap): List<Classification> {
        return landmarkClassifier.classify(bitmap)
            .take(1)
    }
}