package com.example.banksampah.utill

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

class Session(
    context: Context
) {

    companion object {
        const val TOKEN_KEY = "token"
    }

    private val pref: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    var token: String?
        get() = pref.getString(TOKEN_KEY, "")
        set(value) = pref.edit().putString(TOKEN_KEY, value).apply()

}