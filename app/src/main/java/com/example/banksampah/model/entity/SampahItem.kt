package com.example.banksampah.model.entity

import com.google.gson.annotations.SerializedName

class SampahItem(
    @SerializedName("_id")
    var id: String? = "",
    @SerializedName("k_name")
    var kName: String? = ""
)