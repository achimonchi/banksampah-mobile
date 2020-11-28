package com.example.banksampah.utill

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadImageFromUrl")
fun ImageView.loadImageFromUrl(url: String?) {
    val fullUrl = Constant.BASE_URL_IMG_SAMPAH + url
    Glide.with(this.context).load(fullUrl).into(this)
}