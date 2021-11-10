package com.basada.banksampah.model.response

import com.basada.banksampah.model.entity.SampahItem
import com.google.gson.annotations.SerializedName

class SampahResponse(
    @SerializedName("data")
    var `data`: List<SampahItem?>? = listOf()
)