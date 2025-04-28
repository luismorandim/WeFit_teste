package com.example.wefit_teste.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.wefit_teste.common.AppColors

@Composable
fun PlaceholderScreen(tabIndex: Int, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(AppColors.BackgroundDark),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = when (tabIndex) {
                0 -> "Carrinho está vazio."
                2 -> "Perfil em construção."
                else -> ""
            },
            color = AppColors.White
        )
    }
}
