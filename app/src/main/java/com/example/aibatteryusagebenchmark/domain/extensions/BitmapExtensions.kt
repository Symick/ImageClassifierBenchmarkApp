package com.example.aibatteryusagebenchmark.domain.extensions

import android.graphics.Bitmap

fun Bitmap.centerCrop(desiredHeight: Int, desiredWidth: Int): Bitmap {
    val xStart = (width - desiredWidth) / 2
    val yStart = (height - desiredHeight) / 2

    return Bitmap.createBitmap(this, xStart, yStart, desiredWidth, desiredHeight)
}