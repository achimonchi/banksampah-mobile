package com.basada.banksampah.model.entity


import com.google.gson.annotations.SerializedName

data class RequestAdminSampahItem(
    @SerializedName("fk_auth")
    var fkAuth: String? = "",
    @SerializedName("_id")
    var id: String? = "",
    @SerializedName("isActive")
    var isActive: String? = "",
    @SerializedName("un_name")
    var unName: String? = ""
) {

    override fun toString(): String {
        return "$unName"
    }
}