package com.example.banksampah.utill

import android.content.ContentResolver
import android.content.Context
import android.net.Uri

object Utill {

    fun getMimeTypeFromUri(uri: Uri?, context: Context): String {
        return if (uri == null) {
            ""
        } else {
            val cR: ContentResolver = context.contentResolver
            cR.getType(uri) ?: ""
        }
    }

    fun isImageFile(uri: Uri?, context: Context): Boolean {
        val fileType = Utill.getMimeTypeFromUri(uri, context)
        val fileExtension = uri.toString().substring(uri.toString().lastIndexOf(".") + 1)

        return fileType.contains("image", true) ||
                fileExtension.contains("jpeg", true) ||
                fileExtension.contains("jpg", true) ||
                fileExtension.contains("png", true)
    }

}