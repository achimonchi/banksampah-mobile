package com.basada.banksampah.model.entity


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class JadwalResponseItem(
    @SerializedName("_id")
    var id: String? = null,
    @SerializedName("s_day")
    var sDay: String? = null,
    @SerializedName("s_time")
    var sTime: String? = null,
    @SerializedName("s_weather")
    var sWeather: String? = null,
    @SerializedName("s_weather_icon")
    var sWeatherIcon: String? = null
) : Parcelable {

    override fun toString(): String {
        return "$sDay: $sTime"
    }
}