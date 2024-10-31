package com.example.aibatteryusagebenchmark.presentation.image

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import org.koin.core.annotation.Factory

@Factory
class BitMapMapper(private val context: Context) {

    operator fun invoke(@DrawableRes imageDrawable: Int): Bitmap? {
        val drawable = ContextCompat.getDrawable(context, imageDrawable)

        if (drawable == null) return null

        if(drawable is BitmapDrawable) {
            return drawable.bitmap
        }


        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )

        // Bind a Canvas to the bitmap and draw the drawable onto the canvas
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)

        return bitmap
    }
}