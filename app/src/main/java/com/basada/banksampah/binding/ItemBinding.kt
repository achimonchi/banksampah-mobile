package com.basada.banksampah.binding

import android.net.Uri
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.basada.banksampah.R
import com.basada.banksampah.utill.Constant
import java.util.*

@BindingAdapter("loadImageFromUrl")
fun ImageView.loadImageFromUrl(url: String?) {
    val fullUrl = Constant.BASE_URL_IMG_SAMPAH + url
    Glide.with(this.context).load(fullUrl).into(this)
}

@BindingAdapter("setImageWithURI")
fun ImageView.setImageWithURI(uri: Uri?) {
    this.setImageURI(uri)
}

@BindingAdapter("setStatusColor")
fun TextView.setStatusColor(status: String?) {
    status?.let {
        when {
            it.contains("belum", true) -> {
                setTextColor(ContextCompat.getColor(context, R.color.colorYellow))
            }
            it.contains("masuk", true) -> {
                setTextColor(ContextCompat.getColor(context, R.color.colorGreen))
            }
            else -> {
                setTextColor(ContextCompat.getColor(context, R.color.colorRed))
            }
        }
    }
}