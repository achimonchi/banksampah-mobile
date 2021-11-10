package com.basada.banksampah.model.response


import android.os.Parcelable
import com.basada.banksampah.model.entity.ChangePasswordItem
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ChangePasswrodResponse(
    @SerializedName("data")
    var changePasswordItem: ChangePasswordItem? = null,
    @SerializedName("status")
    var status: Int? = null
) : Parcelable