package com.example.moviebooking.Repository

import android.graphics.Movie
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviebooking.Model.PopularMoviesModel
import com.example.moviebooking.Network.MovieService

class PopularMoviesRepository(private val movieService : MovieService) {


    private val mutablePopularMoviesLiveData =  MutableLiveData<PopularMoviesModel>()


    val popularMovieLiveData : LiveData<PopularMoviesModel>
    get() = mutablePopularMoviesLiveData

     suspend fun getPopularMovies()
     {
          val result = movieService.getPopularMovies()
          if(result?.body() != null)
          {
               mutablePopularMoviesLiveData.postValue(result.body())
          }

     }



}