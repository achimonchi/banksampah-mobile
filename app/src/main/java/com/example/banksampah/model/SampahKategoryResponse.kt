package com.example.banksampah.model


import com.google.gson.annotations.SerializedName

class SampahKategoryResponse(
    @SerializedName("data")
    var `data`: List<SampahKategoryItem?>? = listOf()
)