package com.example.banksampah.utill

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import java.io.File
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

object Utill {

    private fun getMimeTypeFromUri(uri: Uri?, context: Context): String {
        return if (uri == null) {
            ""
        } else {
            val cR: ContentResolver = context.contentResolver
            cR.getType(uri) ?: ""
        }
    }

    fun isImageFile(uri: Uri?, context: Context): Boolean {
        val fileType = getMimeTypeFromUri(uri, context)
        val fileExtension = uri.toString().substring(uri.toString().lastIndexOf(".") + 1)

        return fileType.contains("image", true) ||
                fileExtension.contains("jpeg", true) ||
                fileExtension.contains("jpg", true) ||
                fileExtension.contains("png", true)
    }

    fun uriToFile(uri: Uri?): File {
        return File(uri?.path ?: "")
    }

    fun stringToRp(jumlah: String): String {
        val kurs = (DecimalFormat.getCurrencyInstance() as DecimalFormat)
        val format = DecimalFormatSymbols().apply {
            currencySymbol = "Rp. "
            groupingSeparator = '.'
            monetaryDecimalSeparator = '.'
        }

        kurs.decimalFormatSymbols = format

        return kurs.format(jumlah.toInt())
    }

}