package com.example.aibatteryusagebenchmark.data

import android.content.Context
import android.graphics.Bitmap
import com.example.aibatteryusagebenchmark.domain.data.LandmarkClassifier
import com.example.aibatteryusagebenchmark.domain.extensions.centerCrop
import com.example.aibatteryusagebenchmark.domain.model.Classification
import com.example.aibatteryusagebenchmark.presentation.image.BitMapMapper
import com.example.aibatteryusagebenchmark.presentation.image.GetImage
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Named

@Factory
@Named("NoOp")
class NoOpLandmarkClassifier(val context: Context, val bitMapMapper: BitMapMapper) : LandmarkClassifier {

    override suspend fun classify(bitmap: Bitmap): List<Classification> {
        val id = GetImage.drawables.find { bitMapMapper(it)?.centerCrop(321, 321)?.sameAs(bitmap) == true }
        return listOf(
            Classification(
                id?.let { context.resources.getResourceEntryName(it) } ?: "",
                0.9f
            )
        )
    }
}