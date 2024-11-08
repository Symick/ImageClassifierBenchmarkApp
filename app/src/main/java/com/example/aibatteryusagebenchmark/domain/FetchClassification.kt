package com.example.aibatteryusagebenchmark.domain

import android.graphics.Bitmap
import com.example.aibatteryusagebenchmark.domain.data.LandmarkClassifier
import com.example.aibatteryusagebenchmark.domain.extensions.centerCrop
import com.example.aibatteryusagebenchmark.domain.model.Classification
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Named

@Factory
class FetchClassification(@Named("NoOp") private val landmarkClassifier: LandmarkClassifier) {

    suspend operator fun invoke(bitmap: Bitmap): Classification {
        return landmarkClassifier.classify(
            bitmap.centerCrop(
                desiredHeight = DesiredSize,
                desiredWidth = DesiredSize
            )
        ).first()
    }

    private companion object {

        const val DesiredSize = 321
    }
}