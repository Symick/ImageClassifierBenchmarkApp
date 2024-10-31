package com.example.aibatteryusagebenchmark.data

import android.graphics.Bitmap
import com.example.aibatteryusagebenchmark.domain.data.LandmarkClassifier
import com.example.aibatteryusagebenchmark.domain.model.Classification
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Named
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.task.vision.classifier.ImageClassifier

@Factory
@Named("liteRt")
class LiteRTLandmarkClassifier(
    private val imageClassifier: ImageClassifier,
    private val imageProcessor: ImageProcessor
): LandmarkClassifier {

    override suspend fun classify(bitmap: Bitmap): List<Classification> {
        return imageClassifier.classify(imageProcessor.process(TensorImage.fromBitmap(bitmap)))
            .flatMap { classification ->
                classification.categories.map {
                    Classification(
                        it.displayName,
                        it.score
                    )
                }
            }
            .distinctBy { it.name }
    }
}