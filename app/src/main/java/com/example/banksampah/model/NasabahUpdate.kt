package com.example.banksampah.model


import com.google.gson.annotations.SerializedName

data class NasabahUpdate(
    @SerializedName("n_address")
    var nAddress: String? = "",
    @SerializedName("n_city")
    var nCity: String? = "",
    @SerializedName("n_contact")
    var nContact: String? = "",
    @SerializedName("n_name")
    var nName: String? = "",
    @SerializedName("n_postcode")
    var nPostcode: String? = "",
    @SerializedName("n_province")
    var nProvince: String? = ""
)