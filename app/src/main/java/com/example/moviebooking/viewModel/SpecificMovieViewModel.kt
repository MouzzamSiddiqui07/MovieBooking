package com.example.moviebooking.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviebooking.model.SpecificMovieModel
import com.example.moviebooking.repository.SpecificMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpecificMovieViewModel @Inject constructor(private val specificMovieRepo : SpecificMovieRepository) : ViewModel (){


    fun getSpecificMovieDetails(movieId : Int)
    {
        viewModelScope.launch (Dispatchers.IO){
            specificMovieRepo.getSpecificMovieDetails(movieId)
        }
    }

    val specificMovieLiveData : LiveData<SpecificMovieModel>
    get() = specificMovieRepo.specificMovieLiveData

}