package com.example.moviebooking.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviebooking.Model.SpecificMovieModel
import com.example.moviebooking.Repository.SpecificMovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SpecificMovieViewModel(private val specificMovieRepo : SpecificMovieRepository) : ViewModel (){


    fun getSpecificMovieDetails(movieId : Int)
    {
        viewModelScope.launch (Dispatchers.IO){
            specificMovieRepo.getSpecificMovieDetails(movieId)
        }
    }

    val specificMovieLiveData : LiveData<SpecificMovieModel>
    get() = specificMovieRepo.specificMovieLiveData

}