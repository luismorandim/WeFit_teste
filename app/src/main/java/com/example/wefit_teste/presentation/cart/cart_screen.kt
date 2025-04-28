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
import com.example.wefit_teste.data.model.Movie
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
                .padding(16.dp)
        ) {
            Text(
                text = "Carrinho de compras",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = AppColors.White
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                colors = CardDefaults.cardColors(containerColor = AppColors.White)
            ) {
                if (cartItems.isEmpty()) {
                    EmptyState(
                        buttonText = "Voltar à Home",
                        onButtonClick = onNavigateHome
                    )
                } else {
                    Column(modifier = Modifier.padding(16.dp)) {
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(cartItems) { (movie, cartItemData) ->
                                CartItemCard(
                                    movie = movie,
                                    cartItem = cartItemData,
                                    onIncrease = { onIncreaseQuantity(movie) },
                                    onDecrease = { onDecreaseQuantity(movie) },
                                    onRemove = { showDialog = movie to cartItemData }
                                )
                            }
                        }

                        Divider(
                            color = AppColors.LightGray,
                            thickness = 1.dp,
                            modifier = Modifier.padding(vertical = 16.dp)
                        )

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

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = {
                                showSuccess = true
                                onFinalizeOrder()
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = AppColors.PrimaryBlue,
                                contentColor = AppColors.White
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(text = "FINALIZAR PEDIDO")
                        }
                    }
                }
            }
        }
    }

    if (showDialog != null) {
        AlertDialog(
            onDismissRequest = { showDialog = null },
            confirmButton = {
                TextButton(onClick = {
                    onRemoveItem(showDialog!!.first)
                    showDialog = null
                }) {
                    Text("Sim", color = AppColors.SuccessGreen)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = null }) {
                    Text("Cancelar")
                }
            },
            title = { Text("Remover item") },
            text = { Text("Tem certeza que deseja remover este item do carrinho?") }
        )
    }
}
