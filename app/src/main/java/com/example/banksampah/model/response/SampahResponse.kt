package com.example.banksampah.model.response

import com.example.banksampah.model.entity.SampahItem
import com.google.gson.annotations.SerializedName

class SampahResponse(
    @SerializedName("data")
    var `data`: List<SampahItem?>? = listOf()
)