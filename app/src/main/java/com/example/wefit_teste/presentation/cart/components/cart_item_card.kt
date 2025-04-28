package com.example.wefit_teste.presentation.cart.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.wefit_teste.common.AppColors
import com.example.wefit_teste.data.model.Movie
import com.example.wefit_teste.presentation.home.viewmodel.CartItemData
import com.example.wefit_teste.utils.FormatUtils

@Composable
fun CartItemCard(
    movie: Movie,
    cartItem: CartItemData,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit,
    onRemove: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = AppColors.White),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = rememberAsyncImagePainter(movie.image),
                    contentDescription = "Imagem do Filme",
                    modifier = Modifier.size(80.dp),
                    contentScale = ContentScale.Fit
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = movie.title,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = AppColors.Black,
                        maxLines = 1
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Adicionado em ${cartItem.addedAt}",
                        style = MaterialTheme.typography.bodySmall,
                        color = AppColors.LightGray
                    )
                }
                IconButton(onClick = onRemove) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Remover",
                        tint = AppColors.PrimaryBlue
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onDecrease) {
                        Icon(
                            imageVector = Icons.Default.Remove,
                            contentDescription = "Diminuir",
                            tint = AppColors.PrimaryBlue
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = cartItem.quantity.toString(),
                        style = MaterialTheme.typography.titleLarge.copy(
                            color = AppColors.Black,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.width(32.dp),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    IconButton(onClick = onIncrease) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Aumentar",
                            tint = AppColors.PrimaryBlue
                        )
                    }
                }

                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "SUBTOTAL",
                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                        color = AppColors.LightGray
                    )
                    Text(
                        text = FormatUtils.formatCurrency(movie.price * cartItem.quantity),
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = AppColors.Black
                    )
                }
            }
        }
    }
}
