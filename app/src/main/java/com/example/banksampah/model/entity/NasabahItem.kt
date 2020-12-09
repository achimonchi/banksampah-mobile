package com.example.banksampah.model.entity


import com.google.gson.annotations.SerializedName

data class NasabahItem(
    @SerializedName("fk_auth")
    var fkAuth: String? = "",
    @SerializedName("_id")
    var id: String? = "",
    @SerializedName("isExist")
    var isExist: String? = "",
    @SerializedName("n_address")
    var nAddress: String? = "",
    @SerializedName("n_balance")
    var nBalance: String? = "",
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