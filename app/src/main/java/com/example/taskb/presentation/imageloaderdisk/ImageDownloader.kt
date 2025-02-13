package com.example.taskb.presentation.imageloaderdisk

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.core.content.ContextCompat
import com.example.taskb.R
import java.io.File
import java.io.FileOutputStream

object ImageDownloader {

    fun saveImagesToInternal(context: Context) {
        val imageIds = listOf(
            R.drawable.image21, R.drawable.image22, R.drawable.image23,
        )
        val svgIds = listOf(
            R.raw.image11, R.raw.image12
        )

        val imagesDir = File(context.filesDir, "images").apply { mkdirs() }
        imageIds.forEachIndexed { index, resId ->
            val drawable = ContextCompat.getDrawable(context, resId)
            if (drawable is BitmapDrawable) {
                val bitmap = drawable.bitmap
                val file = File(imagesDir, "image_$index.jpg")

                FileOutputStream(file).use { out ->
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
                }
            }
        }
        svgIds.forEachIndexed { index, resId ->
            val file = File(imagesDir, "image_svg_$index.svg")
            context.resources.openRawResource(resId).use { inputStream ->
                file.outputStream().use { outputStream ->
                    inputStream.copyTo(outputStream)
                }
            }
        }
    }
}
