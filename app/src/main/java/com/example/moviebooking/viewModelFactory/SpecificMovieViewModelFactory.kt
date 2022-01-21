package com.example.moviebooking.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviebooking.repository.SpecificMovieRepository
import com.example.moviebooking.viewModel.SpecificMovieViewModel

class SpecificMovieViewModelFactory(private val specificMovieDetailsRepo : SpecificMovieRepository) : ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SpecificMovieViewModel(specificMovieDetailsRepo) as T
    }

}