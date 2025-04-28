package com.example.wefit_teste.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.wefit_teste.common.AppColors
import com.example.wefit_teste.infra.model.Movie
import com.example.wefit_teste.common.Spacings

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    movies: List<Movie>,
    cartItems: Map<Int, Int>,
    onAddToCart: (Int) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(AppColors.BackgroundDark)
            .padding(horizontal = Spacings.spacing(16))
    ) {
        Spacer(modifier = Modifier.height(Spacings.spacing(16)))
        Text(
            text = "Mais vendidos",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold
            ),
            color = AppColors.White
        )
        Spacer(modifier = Modifier.height(Spacings.spacing(4)))
        Text(
            text = "Maiores sucessos do WeMovies",
            style = MaterialTheme.typography.bodyLarge,
            color = AppColors.White,
            modifier = Modifier.padding(bottom = Spacings.spacing(16))
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(Spacings.spacing(16))
        ) {
            items(movies) { movie ->
                val quantity = cartItems[movie.id] ?: 0
                MovieCard(movie, quantity, onAddToCart)
            }
        }
    }
}
