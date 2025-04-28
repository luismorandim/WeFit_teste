package com.example.wefit_teste.presentation.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.wefit_teste.common.AppColors
import com.example.wefit_teste.common.Spacings
import com.example.wefit_teste.infra.model.Movie
import com.example.wefit_teste.presentation.cart.components.CartItemCard
import com.example.wefit_teste.presentation.components.EmptyState
import com.example.wefit_teste.presentation.home.viewmodel.CartItemData
import com.example.wefit_teste.presentation.success.SuccessScreen
import com.example.wefit_teste.utils.FormatUtils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    cartItems: List<Pair<Movie, CartItemData>>,
    onIncreaseQuantity: (Movie) -> Unit,
    onDecreaseQuantity: (Movie) -> Unit,
    onRemoveItem: (Movie) -> Unit,
    onNavigateHome: () -> Unit,
    onFinalizeOrder: () -> Unit,
    modifier: Modifier = Modifier
) {
    var showDialog by remember { mutableStateOf<Pair<Movie, CartItemData>?>(null) }
    var moviePendingRemoval by remember { mutableStateOf<Movie?>(null) }
    var showSuccess by remember { mutableStateOf(false) }

    if (showSuccess) {
        SuccessScreen(
            onNavigateHome = {
                showSuccess = false
                onNavigateHome()
                onFinalizeOrder()
            }
        )
    } else {
        val totalPrice = cartItems.sumOf { it.first.price * it.second.quantity }

        Column(
            modifier = modifier
                .fillMaxSize()
                .background(AppColors.BackgroundDark)
                .padding(Spacings.spacing(16))
        ) {
            Text(
                text = "Carrinho de compras",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = AppColors.White
                )
            )

            Spacer(modifier = Modifier.height(Spacings.spacing(16)))

            Card(
                shape = RoundedCornerShape(Spacings.spacing(12)),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                colors = CardDefaults.cardColors(containerColor = AppColors.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(Spacings.spacing(16))
                ) {
                    if (cartItems.isEmpty()) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            EmptyState(
                                buttonText = "Voltar à Home",
                                onButtonClick = onNavigateHome
                            )
                        }
                    } else {
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(Spacings.spacing(12)),
                            modifier = Modifier.weight(1f)
                        ) {
                            items(cartItems) { (movie, cartItemData) ->
                                CartItemCard(
                                    movie = movie,
                                    cartItem = cartItemData,
                                    onIncrease = { onIncreaseQuantity(movie) },
                                    onDecrease = {
                                        if (cartItemData.quantity == 1) {
                                            moviePendingRemoval = movie
                                        } else {
                                            onDecreaseQuantity(movie)
                                        }
                                    },
                                    onRemove = { showDialog = movie to cartItemData }
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(Spacings.spacing(16)))

                        Divider(
                            color = AppColors.LightGray,
                            thickness = 1.dp
                        )

                        Spacer(modifier = Modifier.height(Spacings.spacing(16)))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "TOTAL",
                                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                                color = AppColors.Black
                            )
                            Text(
                                text = FormatUtils.formatCurrency(totalPrice),
                                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                                color = AppColors.Black
                            )
                        }

                        Spacer(modifier = Modifier.height(Spacings.spacing(16)))

                        Button(
                            onClick = {
                                showSuccess = true
                                onFinalizeOrder()
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(Spacings.spacing(48)),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = AppColors.PrimaryBlue,
                                contentColor = AppColors.White
                            ),
                            shape = RoundedCornerShape(Spacings.spacing(8))
                        ) {
                            Text(text = "FINALIZAR PEDIDO")
                        }
                    }
                }
            }
        }
    }

    RemoveItemDialog(
        movie = showDialog?.first,
        onDismiss = { showDialog = null },
        onConfirm = {
            onRemoveItem(showDialog!!.first)
            showDialog = null
        }
    )

    RemoveItemDialog(
        movie = moviePendingRemoval,
        onDismiss = { moviePendingRemoval = null },
        onConfirm = {
            onRemoveItem(moviePendingRemoval!!)
            moviePendingRemoval = null
        }
    )
}

@Composable
fun RemoveItemDialog(
    movie: Movie?,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    if (movie != null) {
        AlertDialog(
            onDismissRequest = onDismiss,
            confirmButton = {
                TextButton(onClick = onConfirm) {
                    Text("Sim", color = AppColors.SuccessGreen)
                }
            },
            dismissButton = {
                TextButton(onClick = onDismiss) {
                    Text("Cancelar")
                }
            },
            title = { Text("Remover item") },
            text = { Text("Tem certeza que deseja remover este item do carrinho?") }
        )
    }
}
