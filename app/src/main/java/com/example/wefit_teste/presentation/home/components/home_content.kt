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
import com.example.wefit_teste.data.model.Movie

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
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Mais vendidos",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold
            ),
            color = AppColors.White
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Maiores sucessos do WeMovies",
            style = MaterialTheme.typography.bodyLarge,
            color = AppColors.White,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(movies) { movie ->
                val quantity = cartItems[movie.id] ?: 0
                MovieCard(movie, quantity, onAddToCart)
            }
        }
    }
}
