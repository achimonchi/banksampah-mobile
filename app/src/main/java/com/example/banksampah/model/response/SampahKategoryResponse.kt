package com.example.banksampah.model.response


import com.example.banksampah.model.entity.SampahKategoryItem
import com.google.gson.annotations.SerializedName

class SampahKategoryResponse(
    @SerializedName("data")
    var `data`: List<SampahKategoryItem?>? = listOf()
)