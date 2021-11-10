package com.basada.banksampah.model.response


import android.os.Parcelable
import com.basada.banksampah.model.entity.ArtikelResponseItem
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArtikelResponse(
    @SerializedName("data")
    var `data`: List<ArtikelResponseItem>? = null
) : Parcelable