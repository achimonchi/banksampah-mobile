package com.example.banksampah.model.entity

import com.google.gson.annotations.SerializedName

class AuthItem(
    @SerializedName("data")
    var `data`: Data? = Data(),
    @SerializedName("message")
    var message: String? = "",
    @SerializedName("token")
    var token: String? = ""
) {
    data class Data(
        @SerializedName("email")
        var email: String? = "",
        @SerializedName("password")
        var password: String? = "",
        @SerializedName("role")
        var role: String? = ""
    )
}