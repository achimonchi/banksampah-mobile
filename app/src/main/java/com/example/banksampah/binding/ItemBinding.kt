package com.example.banksampah.binding

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.banksampah.utill.Constant

@BindingAdapter("loadImageFromUrl")
fun ImageView.loadImageFromUrl(url: String?) {
    val fullUrl = Constant.BASE_URL_IMG_SAMPAH + url
    Glide.with(this.context).load(fullUrl).into(this)
}

@BindingAdapter("setImageWithURI")
fun ImageView.setImageWithURI(uri: Uri?) {
    this.setImageURI(uri)
}