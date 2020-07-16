package com.jhonr1.wishlist.helpers

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import com.jhonr1.wishlist.BuildConfig
import java.io.File
/**
 * Get image location
 */
class ImageCapture(private val context: Context) {
    lateinit var imgFile: File
    lateinit var imgUri: Uri

    fun prepare(): Uri {
        imgFile = Image.create()
        imgUri = FileProvider.getUriForFile(
            context,
            BuildConfig.APPLICATION_ID + ".provider",
            imgFile
        )
        return imgUri
    }
}