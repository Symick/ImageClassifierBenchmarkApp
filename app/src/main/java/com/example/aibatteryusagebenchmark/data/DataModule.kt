package com.example.aibatteryusagebenchmark.data

import android.content.Context
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.task.core.BaseOptions
import org.tensorflow.lite.task.vision.classifier.ImageClassifier

@Module
@ComponentScan
class DataModule {

    @Factory
    fun provideBaseOptions(): BaseOptions {
        return BaseOptions.builder()
            .setNumThreads(NumThreads)
            .build()
    }

    @Factory
    fun provideImageClassifierOptions(baseOptions: BaseOptions): ImageClassifier.ImageClassifierOptions {
        return ImageClassifier.ImageClassifierOptions.builder()
            .setBaseOptions(baseOptions)
            .setScoreThreshold(Treshhold)
            .setMaxResults(MaxResult)
            .build()
    }

    @Factory
    fun provideImageClassifier(
        context: Context,
        options: ImageClassifier.ImageClassifierOptions
    ): ImageClassifier {
        return ImageClassifier.createFromFileAndOptions(context, ModelFile, options)
    }

    @Factory
    fun provideImageProcessor(): ImageProcessor {
        return ImageProcessor.Builder()
            .build()
    }

    companion object {

        const val Treshhold = 0.5F
        const val NumThreads = 2
        const val MaxResult = 5
        const val ModelFile = "landmarks.tflite"
    }
}