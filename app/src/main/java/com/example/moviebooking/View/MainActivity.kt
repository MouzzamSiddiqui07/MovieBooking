package com.example.moviebooking.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.core.content.withStyledAttributes
import androidx.core.widget.NestedScrollView
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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    lateinit var popularMoviesViewModel : PopularMoviesViewModel
    lateinit var popularMovieRecyclerView : RecyclerView

    lateinit var gridLayoutManager: GridLayoutManager
    lateinit var popularMoviesList : List<Result>

    private val lastVisibleItemPosition: Int
        get() = gridLayoutManager.findLastVisibleItemPosition()

    private lateinit var popularMoviesAdapter: PopularMoviesAdapter

    lateinit var popularMoviesRepository : PopularMoviesRepository


    //nested scroll view object
    lateinit var nestedScrollView : NestedScrollView
    lateinit var  progressBar : ProgressBar
    var pageCount = 1
    var totalPageCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //init recycler view
        popularMovieRecyclerView = findViewById(R.id.popularMovieRecyclerView)
        nestedScrollView  = findViewById(R.id.scrollView)
        progressBar = findViewById(R.id.progressBar)
        popularMoviesList = mutableListOf()


        gridLayoutManager = GridLayoutManager(this , 2 , GridLayoutManager.VERTICAL , false)
        popularMovieRecyclerView.layoutManager = gridLayoutManager




        //create view model instance
//        popularMoviesViewModel = ViewModelProvider(this , PopularMoviesViewModelFactory(popularMoviesRepository)).get(PopularMoviesViewModel :: class.java)
        popularMoviesViewModel = ViewModelProvider(this).get(PopularMoviesViewModel :: class.java)
        popularMoviesViewModel.getPopularMovies(pageCount)



        // observe live data of popular movies
        popularMoviesViewModel.popularMoviesLiveData.observe(this , {
               val popularMoviesModel =  it
               totalPageCount = it.totalPages
               (popularMoviesList as MutableList<Result>).addAll( popularMoviesModel.results)
            popularMoviesAdapter = PopularMoviesAdapter(this , popularMoviesList)
            popularMovieRecyclerView.adapter = popularMoviesAdapter


        })

        nestedScrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if(scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight)
            {
                popularMoviesViewModel.getPopularMovies(++pageCount)
            }
        })


    }

}