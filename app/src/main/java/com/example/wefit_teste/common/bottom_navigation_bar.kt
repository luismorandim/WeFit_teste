package com.example.wefit_teste.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.wefit_teste.common.AppColors
import com.example.wefit_teste.common.Spacings

@Composable
fun BottomNavigationBar(selectedTab: Int, onTabSelected: (Int) -> Unit, cartItemCount: Int) {
    Surface(
        color = AppColors.BackgroundDark,
        tonalElevation = Spacings.spacing(0)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(Spacings.spacing(64))
        ) {
            listOf(
                Triple(Icons.Default.ShoppingCart, if (cartItemCount > 0) "Carrinho ($cartItemCount)" else "Carrinho", true),
                Triple(Icons.Default.Home, "Home", true),
                Triple(Icons.Default.Person, "Perfil", false)
            ).forEachIndexed { index, item ->
                val isEnabled = item.third

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clickable(enabled = isEnabled) { if (isEnabled) onTabSelected(index) }
                ) {
                    if (selectedTab == index && isEnabled) {
                        Box(
                            Modifier
                                .align(Alignment.TopCenter)
                                .height(2.dp)
                                .fillMaxWidth(0.6f)
                                .background(AppColors.White)
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.align(Alignment.Center)
                    ) {
                        Icon(
                            imageVector = item.first,
                            contentDescription = item.second,
                            tint = when {
                                !isEnabled -> AppColors.LightGray.copy(alpha = 0.5f)
                                selectedTab == index -> AppColors.White
                                else -> AppColors.LightGray
                            },
                            modifier = Modifier.size(Spacings.spacing(20))
                        )
                        Spacer(modifier = Modifier.width(Spacings.spacing(4)))
                        Text(
                            text = item.second,
                            color = when {
                                !isEnabled -> AppColors.LightGray.copy(alpha = 0.5f)
                                selectedTab == index -> AppColors.White
                                else -> AppColors.LightGray
                            }
                        )
                    }
                }
            }
        }
    }
}
