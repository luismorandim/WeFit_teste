package com.example.wefit_teste.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.wefit_teste.common.AppColors
import com.example.wefit_teste.data.model.Movie
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import com.example.wefit_teste.utils.FormatUtils

@Composable
fun MovieCard(movie: Movie, quantity: Int, onAddToCart: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        colors = CardDefaults.cardColors(containerColor = AppColors.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberAsyncImagePainter(movie.image),
                contentDescription = "Imagem do Filme",
                modifier = Modifier
                    .size(150.dp)
                    .padding(top = 8.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = movie.title,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = AppColors.Black,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = FormatUtils.formatCurrency(movie.price),
                style = MaterialTheme.typography.bodyMedium,
                color = AppColors.Black
            )
            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = { onAddToCart(movie.id) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (quantity > 0) AppColors.SuccessGreen else AppColors.PrimaryBlue,
                    contentColor = AppColors.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Carrinho",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "${if (quantity > 0) quantity else ""}  ADICIONAR AO CARRINHO")
            }
        }
    }
}
