package com.example.banksampah.model


import com.google.gson.annotations.SerializedName

data class SampahResponse(
    @SerializedName("data")
    var `data`: List<Data?>? = listOf()
) {
    data class Data(
        @SerializedName("_id")
        var id: String? = "",
        @SerializedName("k_name")
        var kName: String? = ""
    )
}