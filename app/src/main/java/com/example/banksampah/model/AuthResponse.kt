package com.example.banksampah.model


import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("data")
    var `data`: Data? = Data(),
    @SerializedName("status")
    var status: Int? = 0
) {
    data class Data(
        @SerializedName("message")
        var message: String? = "",
        @SerializedName("token")
        var token: String? = ""
    )
}