package com.example.moviebooking.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviebooking.Model.SpecificMovieModel
import com.example.moviebooking.Network.MovieService
import javax.inject.Inject

class SpecificMovieRepository @Inject constructor(private val movieService : MovieService) {


    private val mutableSpecificMovieLiveData =  MutableLiveData<SpecificMovieModel>()

    val specificMovieLiveData : LiveData<SpecificMovieModel>
    get() = mutableSpecificMovieLiveData


    suspend fun getSpecificMovieDetails(movieId : Int)
    {
        val result = movieService.getMovieDetails(movieId)
        if(result?.body() != null)
        {
            Log.d("kkk","specific movie response : "+result.body())
            mutableSpecificMovieLiveData.postValue(result.body())
        }
    }

}