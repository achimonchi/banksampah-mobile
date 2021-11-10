package com.basada.banksampah.model.response


import com.basada.banksampah.model.entity.AuthItem
import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("data")
    var `data`: AuthItem? = AuthItem(),
    @SerializedName("status")
    var status: Int? = 0
) {

}