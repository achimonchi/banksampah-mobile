package com.basada.banksampah.model.entity

import android.os.Parcelable
import com.basada.banksampah.utill.Utill
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class SampahKategoryItem(
    @SerializedName("fk_kategori")
    var fkKategori: String? = "",
    @SerializedName("_id")
    var id: String? = "",
    @SerializedName("j_image")
    var jImage: String? = "",
    @SerializedName("j_name")
    var jName: String? = "",
    @SerializedName("j_price")
    var jPrice: String? = "",
    @SerializedName("satuan")
    var satuan: String? = ""
) : Parcelable {

    val priceLabel: String
        get() = Utill.stringToRp(this.jPrice ?: "")

}