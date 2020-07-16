package com.jhonr1.wishlist.helpers

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.graphics.drawable.BitmapDrawable
/**
 * Converting image to bitmap
 */
object BitmapProcessor {
    /**
     * Converting image to bitmap
     */
    fun process(imageFile: String): Bitmap {
        val photoBitmap: Bitmap = BitmapFactory.decodeFile(imageFile)
        return scale(photoBitmap)
    }

    private fun scale(bitmap: Bitmap): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(0F)
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width,
            bitmap.height, matrix, true)
    }
    /**
     * Converting bitmap to drawable image
     */
    fun convertBitmapToDrawable(resources: Resources, bitmap: Bitmap): BitmapDrawable {
        return BitmapDrawable(resources, bitmap)
    }
}