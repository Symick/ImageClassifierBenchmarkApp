package com.example.aibatteryusagebenchmark.presentation.image

import androidx.annotation.DrawableRes
import com.example.aibatteryusagebenchmark.R
import org.koin.core.annotation.Factory

/*
* Class that imitates, the user retrieving a photo from their gallery / images,
* but for automating purposes it retrieves a random drawable
* */
@Factory
class GetImage {

    @DrawableRes
    operator fun invoke(): Int {
        return drawables.random()
    }

    private val drawables = listOf(
        R.drawable.stonehenge,
        R.drawable.trevi_fountain,
        R.drawable.arc_de_triomphe,
        R.drawable.eiffel_tower
    )
}