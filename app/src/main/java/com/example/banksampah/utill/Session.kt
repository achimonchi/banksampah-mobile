package com.example.banksampah.utill

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

class Session {

    companion object {
        private const val TOKEN_KEY = "token"

        var preference: SharedPreferences? = null

        fun init(context: Context) {
            preference = PreferenceManager.getDefaultSharedPreferences(context)

        }

        var token: String?
            get() = preference?.getString(TOKEN_KEY, "")
            set(value) {
                preference?.edit()?.putString(TOKEN_KEY, value)?.apply()
            }
    }

}