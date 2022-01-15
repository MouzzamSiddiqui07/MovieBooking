package com.example.moviebooking.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.withStyledAttributes
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviebooking.Adapter.PopularMoviesAdapter
import com.example.moviebooking.Model.Result
import com.example.moviebooking.Network.MovieService
import com.example.moviebooking.Network.RetrofitHelper
import com.example.moviebooking.R
import com.example.moviebooking.Repository.PopularMoviesRepository
import com.example.moviebooking.ViewModel.PopularMoviesViewModel
import com.example.moviebooking.ViewModelFactory.PopularMoviesViewModelFactory

class MainActivity : AppCompatActivity() {


    lateinit var popularMoviesViewModel : PopularMoviesViewModel
    lateinit var popularMovieRecyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //init recycler view
        popularMovieRecyclerView = findViewById(R.id.popularMovieRecyclerView)

        var gridLayoutManager = GridLayoutManager(this , 2 , GridLayoutManager.VERTICAL , false)
        popularMovieRecyclerView.layoutManager = gridLayoutManager


        val popularMovieService = RetrofitHelper.getInstance().create(MovieService :: class.java)

        val popularMovieRepository = PopularMoviesRepository(popularMovieService)

        popularMoviesViewModel = ViewModelProvider(this , PopularMoviesViewModelFactory(popularMovieRepository)).get(PopularMoviesViewModel :: class.java)

        popularMoviesViewModel.popularMoviesLiveData.observe(this , {
               val popularMoviesModel =  it
               val popularMoviesList  : List<Result> =  popularMoviesModel.results
               val popularMoviesAdapter = PopularMoviesAdapter(this , popularMoviesList)
                popularMovieRecyclerView.adapter = popularMoviesAdapter


        })




    }
}