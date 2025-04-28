package com.example.wefit_teste.common

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object Spacings {

    private val spacingList = listOf(
        0.dp,
        4.dp,
        8.dp,
        12.dp,
        16.dp,
        20.dp,
        24.dp,
        28.dp,
        32.dp,
        36.dp,
        40.dp,
        44.dp,
        48.dp,
        52.dp,
        56.dp,
        60.dp,
        64.dp
    )

    fun spacing(position: Int): Dp {
        return when (position) {
            in 0..64 step 4 -> spacingList[position / 4]
            else -> throw IllegalArgumentException(
                "Spacing inválido: $position. Use apenas múltiplos de 4 entre 0 e 64."
            )
        }
    }
}
