package com.example.wefit_teste.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wefit_teste.data.model.Movie
import com.example.wefit_teste.data.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class HomeViewModel : ViewModel() {

    private val repository = MovieRepository()

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> get() = _movies

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _cartItems = MutableStateFlow<Map<Int, CartItemData>>(emptyMap())
    val cartItems: StateFlow<Map<Int, CartItemData>> get() = _cartItems

    fun fetchMovies(onFinished: (() -> Unit)? = null) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = repository.fetchMovies()
                _movies.value = result
            } catch (e: Exception) {
                _movies.value = emptyList()
            } finally {
                _isLoading.value = false
                onFinished?.invoke()
            }
        }
    }

    fun addToCart(movieId: Int) {
        val now = getCurrentFormattedTime()
        _cartItems.value = _cartItems.value.toMutableMap().apply {
            val existing = this[movieId]
            if (existing != null) {
                put(movieId, existing.copy(quantity = existing.quantity + 1))
            } else {
                put(movieId, CartItemData(quantity = 1, addedAt = now))
            }
        }
    }

    fun increaseQuantity(movieId: Int) {
        _cartItems.value = _cartItems.value.toMutableMap().apply {
            val existing = this[movieId]
            if (existing != null) {
                put(movieId, existing.copy(quantity = existing.quantity + 1))
            }
        }
    }

    fun decreaseQuantity(movieId: Int) {
        _cartItems.value = _cartItems.value.toMutableMap().apply {
            val existing = this[movieId]
            if (existing != null) {
                if (existing.quantity > 1) {
                    put(movieId, existing.copy(quantity = existing.quantity - 1))
                } else {
                    remove(movieId)
                }
            }
        }
    }

    fun removeFromCart(movieId: Int) {
        _cartItems.value = _cartItems.value.toMutableMap().apply {
            remove(movieId)
        }
    }

    fun clearCart() {
        _cartItems.value = emptyMap()
    }

    fun updateQuantity(movieId: Int, newQuantity: Int) {
        _cartItems.value = _cartItems.value.toMutableMap().apply {
            if (newQuantity > 0) {
                val existing = this[movieId]
                if (existing != null) {
                    put(movieId, existing.copy(quantity = newQuantity))
                }
            } else {
                remove(movieId)
            }
        }
    }

    private fun getCurrentFormattedTime(): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy 'às' HH:mm", Locale.getDefault())
        return sdf.format(Date())
    }
}

data class CartItemData(
    val quantity: Int,
    val addedAt: String
)
