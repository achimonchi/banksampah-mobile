package com.example.banksampah.model


import com.google.gson.annotations.SerializedName

data class Nasabah(
    @SerializedName("fk_auth")
    var fkAuth: String? = "",
    @SerializedName("_id")
    var id: String? = "",
    @SerializedName("isExist")
    var isExist: String? = "",
    @SerializedName("n_address")
    var nAddress: Any? = Any(),
    @SerializedName("n_balance")
    var nBalance: String? = "",
    @SerializedName("n_city")
    var nCity: Any? = Any(),
    @SerializedName("n_contact")
    var nContact: Any? = Any(),
    @SerializedName("n_name")
    var nName: Any? = Any(),
    @SerializedName("n_postcode")
    var nPostcode: Any? = Any(),
    @SerializedName("n_province")
    var nProvince: Any? = Any()
)