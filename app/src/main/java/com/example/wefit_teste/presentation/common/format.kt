package com.example.wefit_teste.utils

import java.text.NumberFormat
import java.util.Locale

object FormatUtils {

    fun formatCurrency(value: Double): String {
        val currencyFormat = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
        return currencyFormat.format(value)
    }
}
