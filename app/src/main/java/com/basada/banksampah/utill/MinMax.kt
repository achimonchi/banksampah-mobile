package com.basada.banksampah.utill

import android.text.InputFilter
import android.text.Spanned

class MinMax : InputFilter {

    private var min: Double
    private var max: Double

    constructor(min: Double, max: Double) {
        this.min = min
        this.max = max
    }

    constructor(min: String?, max: String?) {
        this.min = min?.toDouble() ?: 0.0
        this.max = max?.toDouble() ?: 0.0
    }

    override fun filter(
        source: CharSequence,
        start: Int,
        end: Int,
        dest: Spanned,
        dstart: Int,
        dend: Int
    ): CharSequence {
        try {
            // limit the number of decimal places
            if (source == "." && dest.toString().isEmpty()) {
                return "0."
            }
            if (dest.toString().contains(".")) {
                val index = dest.toString().indexOf(".")
                val mlength = dest.toString().substring(index).length
                if (mlength == 3) {
                    return ""
                }
            }
            // size limit
            val input = dest.toString().toDouble() + source.toString().toDouble()
            if (isInRange(min, max, input)) return ""
        } catch (nfe: Exception) {
        }
        return ""
    }

    private fun isInRange(a: Double, b: Double, c: Double): Boolean {
        return if (b > a) c >= a && c <= b else c >= b && c <= a
    }

}
