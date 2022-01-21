package com.example.moviebooking.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviebooking.model.SpecificMovieModel
import com.example.moviebooking.network.MovieService
import javax.inject.Inject

class SpecificMovieRepository @Inject constructor(private val movieService : MovieService) {


    private val mutableSpecificMovieLiveData =  MutableLiveData<SpecificMovieModel>()

    val specificMovieLiveData : LiveData<SpecificMovieModel>
    get() = mutableSpecificMovieLiveData


    suspend fun getSpecificMovieDetails(movieId : Int)
    {
        val result = movieService.getMovieDetails(movieId)
        Log.d("kkk","result of repo : "+result.toString())
        if(result?.body() != null)
        {
            Log.d("kkk","specific movie response : "+result.body())
            mutableSpecificMovieLiveData.postValue(result.body())
        }
    }

}