package com.basada.banksampah.model.response


import com.basada.banksampah.model.entity.SampahKategoryItem
import com.google.gson.annotations.SerializedName

class SampahKategoryResponse(
    @SerializedName("data")
    var `data`: List<SampahKategoryItem?>? = listOf()
)