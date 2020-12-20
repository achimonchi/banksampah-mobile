package com.example.banksampah.model.entity


import com.google.gson.annotations.SerializedName

class GetRequestSampahResponseItem(
    @SerializedName("fk_garbage")
    var fkGarbage: String? = "",
    @SerializedName("fk_nasabah")
    var fkNasabah: String? = "",
    @SerializedName("_id")
    var id: String? = "",
    @SerializedName("keterangan")
    var keterangan: String? = "",
    @SerializedName("r_date")
    var rDate: String? = "",
    @SerializedName("r_image")
    var rImage: String? = "",
    @SerializedName("r_notes")
    var rNotes: String? = "",
    @SerializedName("r_request_date")
    var rRequestDate: Any? = Any(),
    @SerializedName("r_status")
    var rStatus: String? = "",
    @SerializedName("r_weight")
    var rWeight: String? = ""
) {
    val weight: String
        get() = "$rWeight Kg"
}