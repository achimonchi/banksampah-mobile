package com.example.banksampah.model.response


import com.example.banksampah.model.entity.AuthItem
import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("data")
    var `data`: AuthItem? = AuthItem(),
    @SerializedName("status")
    var status: Int? = 0
) {

}