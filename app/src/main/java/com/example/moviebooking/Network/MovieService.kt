package com.example.moviebooking.Network

import com.example.moviebooking.Model.PopularMoviesModel
import retrofit2.Response
import retrofit2.http.GET

interface MovieService {


    @GET("movie/popular")
    suspend fun getPopularMovies() : Response<PopularMoviesModel>


}