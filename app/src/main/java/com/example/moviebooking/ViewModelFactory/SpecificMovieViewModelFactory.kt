package com.example.moviebooking.ViewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviebooking.Repository.SpecificMovieRepository
import com.example.moviebooking.ViewModel.PopularMoviesViewModel
import com.example.moviebooking.ViewModel.SpecificMovieViewModel

class SpecificMovieViewModelFactory(private val specificMovieDetailsRepo : SpecificMovieRepository) : ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SpecificMovieViewModel(specificMovieDetailsRepo) as T
    }

}