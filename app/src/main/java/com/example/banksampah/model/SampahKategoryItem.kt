package com.example.banksampah.model

import android.os.Parcel
import android.os.Parcelable
import com.example.banksampah.utill.Utill
import com.google.gson.annotations.SerializedName

class SampahKategoryItem(
    @SerializedName("fk_kategori")
    var fkKategori: String? = "",
    @SerializedName("_id")
    var id: String? = "",
    @SerializedName("j_image")
    var jImage: String? = "",
    @SerializedName("j_name")
    var jName: String? = "",
    @SerializedName("j_price")
    var jPrice: String? = "",
    @SerializedName("satuan")
    var satuan: String? = ""
) : Parcelable {
    val priceLabel: String
        get() = Utill.stringToRp(this.jPrice ?: "")

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(fkKategori)
        parcel.writeString(id)
        parcel.writeString(jImage)
        parcel.writeString(jName)
        parcel.writeString(jPrice)
        parcel.writeString(satuan)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SampahKategoryItem> {
        override fun createFromParcel(parcel: Parcel): SampahKategoryItem {
            return SampahKategoryItem(parcel)
        }

        override fun newArray(size: Int): Array<SampahKategoryItem?> {
            return arrayOfNulls(size)
        }
    }


}