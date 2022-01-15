package com.example.moviebooking.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviebooking.Model.PopularMoviesModel
import com.example.moviebooking.Repository.PopularMoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PopularMoviesViewModel(private val popularMoviesRepo : PopularMoviesRepository)  :
    ViewModel() {


        init {
            viewModelScope.launch(Dispatchers.IO) {
                popularMoviesRepo.getPopularMovies()
            }

        }

    val popularMoviesLiveData : LiveData<PopularMoviesModel>
    get() = popularMoviesRepo.popularMovieLiveData

}