package com.example.borutoandroidcompose.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.palette.graphics.Palette
import androidx.palette.graphics.Target
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import dagger.hilt.android.qualifiers.ApplicationContext

object PaletteGenerator {

    suspend fun convertImageUrlToBitmap(imageUrl: String, @ApplicationContext context: Context): Bitmap? {
        val loader = ImageLoader(context = context)
        val request = ImageRequest.Builder(context = context)
            .data(data = imageUrl)
            .allowHardware(false)
            .build()
        val result = loader.execute(request = request)
        return if (result is SuccessResult)
                (result.drawable as BitmapDrawable).bitmap
            else null
    }

    fun extractColorsFromBitmap(bitmap: Bitmap): Map<String, String> {
        return mapOf(
            Target.VIBRANT.toString() to parseColorSwatch(color = Palette.from(bitmap).generate().vibrantSwatch),
            Target.DARK_VIBRANT.toString() to parseColorSwatch(color = Palette.from(bitmap).generate().darkVibrantSwatch),
            "onDarkVibrant" to parseBodyColor(color = Palette.from(bitmap).generate().darkVibrantSwatch?.bodyTextColor),
        )
    }

    private fun parseColorSwatch(color: Palette.Swatch?): String {
        if (color == null) return "#000000"

        return "#${Integer.toHexString(color.rgb)}"
    }

    private fun parseBodyColor(color: Int?): String {
        if (color == null) return "#FFFFFF"
        return "#${Integer.toHexString(color)}"
    }
}