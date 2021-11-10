package com.basada.banksampah.model.entity


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArtikelResponseItem(
    @SerializedName("a_content")
    var aContent: String? = "",
    @SerializedName("a_file")
    var aFile: String? = "",
    @SerializedName("a_file_type")
    var aFileType: String? = "",
    @SerializedName("a_title")
    var aTitle: String? = "",
    @SerializedName("_id")
    var id: String? = "",
    @SerializedName("k_name")
    var kName: String? = ""
) : Parcelable