package com.example.moviebooking.network

import com.example.moviebooking.model.PopularMoviesModel
import com.example.moviebooking.model.SpecificMovieModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {


    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page") page : Int) : Response<PopularMoviesModel>


    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movieId : Int) : Response<SpecificMovieModel>

}