package com.example.wefit_teste.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wefit_teste.common.AppColors
import com.example.wefit_teste.presentation.cart.CartScreen
import com.example.wefit_teste.presentation.components.BottomNavigationBar
import com.example.wefit_teste.presentation.components.EmptyState
import com.example.wefit_teste.presentation.components.HomeContent
import com.example.wefit_teste.presentation.components.PlaceholderScreen
import com.example.wefit_teste.presentation.home.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = viewModel()

    val movies by viewModel.movies.collectAsState()
    val cartItems by viewModel.cartItems.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    var selectedTab by remember { mutableStateOf(HomeTab.Home) }
    var refreshing by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.fetchMovies()
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("WeMovies") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = AppColors.BackgroundDark,
                    titleContentColor = AppColors.White
                )
            )
        },
        bottomBar = {
            BottomNavigationBar(
                selectedTab = selectedTab.index,
                onTabSelected = { selectedTab = HomeTab.fromIndex(it) },
                cartItemCount = cartItems.values.sumOf { it.quantity }
            )
        },
        containerColor = AppColors.BackgroundDark
    ) { innerPadding ->
        when (selectedTab) {
            HomeTab.Cart -> {
                val cartMovies = movies.filter { cartItems[it.id] != null }
                    .map { movie -> movie to (cartItems[movie.id]!!) }

                CartScreen(
                    cartItems = cartMovies,
                    onIncreaseQuantity = { movie -> viewModel.increaseQuantity(movie.id) },
                    onDecreaseQuantity = { movie -> viewModel.decreaseQuantity(movie.id) },
                    onRemoveItem = { movie -> viewModel.removeFromCart(movie.id) },
                    onNavigateHome = { selectedTab = HomeTab.Home },
                    onFinalizeOrder = { viewModel.clearCart() },
                    modifier = Modifier.padding(innerPadding)
                )
            }

            HomeTab.Home -> {
                when {
                    isLoading && movies.isEmpty() -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(innerPadding),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(color = AppColors.White)
                        }
                    }

                    movies.isEmpty() -> {
                        if (refreshing) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(innerPadding),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(color = AppColors.White)
                            }
                        } else {
                            EmptyState(
                                buttonText = "Recarregar",
                                onButtonClick = {
                                    refreshing = true
                                    viewModel.fetchMovies {
                                        refreshing = false
                                    }
                                }
                            )
                        }
                    }

                    else -> {
                        HomeContent(
                            modifier = Modifier.padding(innerPadding),
                            movies = movies,
                            cartItems = cartItems.mapValues { it.value.quantity },
                            onAddToCart = { movieId -> viewModel.addToCart(movieId) }
                        )
                    }
                }
            }

            HomeTab.Profile -> PlaceholderScreen(tabIndex = selectedTab.index, modifier = Modifier.padding(innerPadding))
        }
    }
}

