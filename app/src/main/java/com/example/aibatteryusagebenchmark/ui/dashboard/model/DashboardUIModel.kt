package com.example.aibatteryusagebenchmark.ui.dashboard.model

import androidx.annotation.DrawableRes
import com.example.aibatteryusagebenchmark.domain.model.Classification

data class DashboardUIModel(
    @DrawableRes val selectedImage: Int,
    val imageSelected: Boolean,
    val classification: Classification? = null
)