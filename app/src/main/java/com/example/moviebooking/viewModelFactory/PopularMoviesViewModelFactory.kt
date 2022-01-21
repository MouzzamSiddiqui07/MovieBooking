package com.example.moviebooking.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviebooking.repository.PopularMoviesRepository
import com.example.moviebooking.viewModel.PopularMoviesViewModel

class PopularMoviesViewModelFactory(private val popularMoviesRepo : PopularMoviesRepository) : ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PopularMoviesViewModel(popularMoviesRepo) as T
    }

}