package com.example.taskb.presentation.imageloaderdisk

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.core.content.ContextCompat
import com.example.taskb.R
import java.io.File
import java.io.FileOutputStream

object ImageDownloader {

    private const val IMAGES_FOLDER = "images"

    fun saveImagesToInternal(context: Context) {
        val imageIds = listOf(
            R.drawable.image21, R.drawable.image22, R.drawable.image23,
        )
        val svgIds = listOf(
            R.raw.image11, R.raw.image12
        )

        val imagesDir = File(context.filesDir, IMAGES_FOLDER).apply { mkdirs() }

        imageIds.forEachIndexed { index, resId ->
            val file = File(imagesDir, "image_$index.jpg")
            if (!file.exists()) {  // ✅ التحقق قبل الحفظ
                val drawable = ContextCompat.getDrawable(context, resId)
                if (drawable is BitmapDrawable) {
                    val bitmap = drawable.bitmap
                    FileOutputStream(file).use { out ->
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
                    }
                }
            }
        }

        svgIds.forEachIndexed { index, resId ->
            val file = File(imagesDir, "image_svg_$index.svg")
            if (!file.exists()) {  // ✅ التحقق قبل الحفظ
                context.resources.openRawResource(resId).use { inputStream ->
                    file.outputStream().use { outputStream ->
                        inputStream.copyTo(outputStream)
                    }
                }
            }
        }
    }

    fun isImagesSaved(context: Context): Boolean {
        val imagesDir = File(context.filesDir, IMAGES_FOLDER)
        return imagesDir.exists() && imagesDir.listFiles()?.isNotEmpty() == true
    }
}
