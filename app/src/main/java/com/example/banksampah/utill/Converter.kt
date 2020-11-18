package com.example.banksampah.utill

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

object Converter {

    fun intToString(jumlah: Int): String {
        val kurs = (DecimalFormat.getCurrencyInstance() as DecimalFormat)
        val format = DecimalFormatSymbols().apply {
            currencySymbol = "Rp. "
            groupingSeparator = ','
            monetaryDecimalSeparator = ','
        }

        kurs.decimalFormatSymbols = format

        return kurs.format(jumlah)
    }

}