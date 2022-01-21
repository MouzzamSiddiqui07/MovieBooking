package com.example.moviebooking.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviebooking.model.PopularMoviesModel
import com.example.moviebooking.repository.PopularMoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor (private val popularMoviesRepo : PopularMoviesRepository)  :
    ViewModel() {

    fun getPopularMovies(pageCount : Int)
    {
        viewModelScope.launch(Dispatchers.IO) {
            popularMoviesRepo.getPopularMovies(pageCount)
        }
    }

    val popularMoviesLiveData : LiveData<PopularMoviesModel>
    get() = popularMoviesRepo.popularMovieLiveData

}