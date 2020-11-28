package com.example.banksampah.model

import com.example.banksampah.utill.Converter
import com.google.gson.annotations.SerializedName

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
) {
    var priceLabel: String? = null

}