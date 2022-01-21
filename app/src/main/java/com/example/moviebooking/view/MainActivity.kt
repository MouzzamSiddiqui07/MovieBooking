package com.example.moviebooking.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviebooking.adapter.PopularMoviesAdapter
import com.example.moviebooking.database.MovieDatabase
import com.example.moviebooking.model.Result
import com.example.moviebooking.R
import com.example.moviebooking.repository.PopularMoviesRepository
import com.example.moviebooking.viewModel.PopularMoviesViewModel
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

    lateinit var movieDatabase : MovieDatabase

    lateinit var favIconImageView : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //init the database
        movieDatabase = MovieDatabase.getDatabaseInstance(this)

        //init the imageView
        favIconImageView = findViewById(R.id.favIcon)

        //when click on Fav Icon
        favIconImageView.setOnClickListener{
            val intent = Intent(applicationContext,FavouritesActivity :: class.java)
            startActivity(intent)
        }



        //init recycler view
        popularMovieRecyclerView = findViewById(R.id.popularMovieRecyclerView)
        nestedScrollView  = findViewById(R.id.scrollView)
        progressBar = findViewById(R.id.spin_kit)
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
            popularMoviesAdapter = PopularMoviesAdapter(this , popularMoviesList , movieDatabase)
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