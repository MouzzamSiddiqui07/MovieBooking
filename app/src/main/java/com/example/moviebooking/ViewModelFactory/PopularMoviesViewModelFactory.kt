package com.example.moviebooking.ViewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviebooking.Repository.PopularMoviesRepository
import com.example.moviebooking.ViewModel.PopularMoviesViewModel

class PopularMoviesViewModelFactory(private val popularMoviesRepo : PopularMoviesRepository) : ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PopularMoviesViewModel(popularMoviesRepo) as T
    }

}