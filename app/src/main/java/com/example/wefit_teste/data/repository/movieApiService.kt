package com.example.wefit_teste.data.repository

import com.example.wefit_teste.data.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET

interface MovieApiService {
    @GET("movies")
    suspend fun getMovies(): Response<MovieResponse>
}
