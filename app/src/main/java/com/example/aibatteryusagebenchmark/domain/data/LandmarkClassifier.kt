package com.example.aibatteryusagebenchmark.domain.data

import android.graphics.Bitmap
import com.example.aibatteryusagebenchmark.domain.model.Classification

interface LandmarkClassifier {

    fun classify(bitmap: Bitmap) : List<Classification>
}