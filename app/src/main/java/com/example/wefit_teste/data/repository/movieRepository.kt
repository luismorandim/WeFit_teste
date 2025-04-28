package com.example.wefit_teste.data.repository

import com.example.wefit_teste.data.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepository {

    suspend fun fetchMovies(): List<Movie> {
        return withContext(Dispatchers.IO) {
            val response = RetrofitInstance.api.getMovies()
            if (response.isSuccessful) {
                response.body()?.products ?: emptyList()
            } else {
                emptyList()
            }
        }
    }
}
