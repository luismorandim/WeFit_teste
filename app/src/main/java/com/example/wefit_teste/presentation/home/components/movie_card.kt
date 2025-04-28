package com.example.wefit_teste.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.wefit_teste.common.AppColors
import com.example.wefit_teste.common.Spacings
import com.example.wefit_teste.infra.model.Movie
import com.example.wefit_teste.utils.FormatUtils
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart

@Composable
fun MovieCard(movie: Movie, quantity: Int, onAddToCart: (Int) -> Unit) {
    val buttonColor = if (quantity > 0) AppColors.SuccessGreen else AppColors.PrimaryBlue
    val quantityText = if (quantity > 0) "$quantity  ADICIONAR AO CARRINHO" else "ADICIONAR AO CARRINHO"

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        colors = CardDefaults.cardColors(containerColor = AppColors.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(Spacings.spacing(12)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberAsyncImagePainter(movie.image),
                contentDescription = "Imagem do Filme",
                modifier = Modifier
                    .size(150.dp)
                    .padding(top = Spacings.spacing(8)),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(Spacings.spacing(12)))

            Text(
                text = movie.title,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = AppColors.Black,
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(Spacings.spacing(8)))

            Text(
                text = FormatUtils.formatCurrency(movie.price),
                style = MaterialTheme.typography.bodyMedium,
                color = AppColors.Black
            )

            Spacer(modifier = Modifier.height(Spacings.spacing(12)))

            Button(
                onClick = { onAddToCart(movie.id) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = buttonColor,
                    contentColor = AppColors.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Spacings.spacing(48))
            ) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Carrinho",
                    modifier = Modifier.size(Spacings.spacing(20))
                )
                Spacer(modifier = Modifier.width(Spacings.spacing(8)))
                Text(text = quantityText)
            }
        }
    }
}
