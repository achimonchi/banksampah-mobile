package com.basada.banksampah.model.entity


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ChangePasswordItem(
    @SerializedName("message")
    var message: String? = "",
    @SerializedName("token")
    var token: String? = "",
    @SerializedName("old_password")
    var oldPassword: String? = "",
    @SerializedName("new_password")
    var newPassword: String? = ""
) : Parcelable