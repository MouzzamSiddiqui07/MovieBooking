package com.example.moviebooking.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviebooking.model.PopularMoviesModel
import com.example.moviebooking.network.MovieService
import javax.inject.Inject

class PopularMoviesRepository @Inject constructor(private val movieService : MovieService) {


    private val mutablePopularMoviesLiveData =  MutableLiveData<PopularMoviesModel>()


    val popularMovieLiveData : LiveData<PopularMoviesModel>
    get() = mutablePopularMoviesLiveData

     suspend fun getPopularMovies(pageCount : Int)
     {

          val result = movieService.getPopularMovies(pageCount)
          if(result?.body() != null)
          {
              Log.d("kkk", "response : $result")
               mutablePopularMoviesLiveData.postValue(result.body())
          }

     }



}