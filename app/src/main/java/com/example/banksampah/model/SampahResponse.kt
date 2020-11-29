package com.example.banksampah.model

import com.google.gson.annotations.SerializedName

class SampahResponse(
    @SerializedName("data")
    var `data`: List<SampahItem?>? = listOf()
)