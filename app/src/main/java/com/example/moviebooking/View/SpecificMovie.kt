package com.example.moviebooking.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.moviebooking.Network.MovieService
import com.example.moviebooking.Network.RetrofitHelper
import com.example.moviebooking.R
import com.example.moviebooking.Repository.SpecificMovieRepository
import com.example.moviebooking.Utils.Credentials
import com.example.moviebooking.ViewModel.SpecificMovieViewModel
import com.example.moviebooking.ViewModelFactory.SpecificMovieViewModelFactory
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.appbar.CollapsingToolbarLayout


class SpecificMovie : AppCompatActivity() {


    lateinit var backArrowImageView : ImageView

    lateinit var specicMovieRepository: SpecificMovieRepository

     lateinit var specificMovieViewModel : SpecificMovieViewModel

    lateinit var moviePosterImageView : ImageView

    lateinit var collapsingToolbar: CollapsingToolbarLayout


    lateinit var releaseDateTextView : TextView
    lateinit var movieTitleTextView : TextView
    lateinit var ratingBar: RatingBar
    lateinit var synopsisTextView: TextView


      var movieId : Int = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_specific_movie)


        backArrowImageView = findViewById(R.id.backArrowImageView2)
        moviePosterImageView = findViewById(R.id.moviePosterImageView)
        collapsingToolbar = findViewById(R.id.collapsingToolBar)
        releaseDateTextView = findViewById(R.id.releaseDateTextView)
        movieTitleTextView = findViewById(R.id.movieTitleTextView)
        ratingBar = findViewById(R.id.ratingBar)
        synopsisTextView = findViewById(R.id.synopsisTextView)

        //when click on back arrow
        backArrowImageView.setOnClickListener{
            onBackPressed()
        }

        //get movie id
        if(intent != null)
        {
            movieId = intent.getIntExtra("movie_id",0)
            Log.d("kkk", "Movie Id : $movieId")
        }


        //create retrofit service
        val specificMovieService  = RetrofitHelper.getInstance().create(MovieService :: class.java)

        //create repository instance
        specicMovieRepository = SpecificMovieRepository(specificMovieService)

        //create  view model instance
        specificMovieViewModel = ViewModelProvider(this , SpecificMovieViewModelFactory(specicMovieRepository)).get(SpecificMovieViewModel:: class.java)
        specificMovieViewModel.getSpecificMovieDetails(movieId)


        //observe live data of specic movie
        specificMovieViewModel.specificMovieLiveData.observe(this , {
            val specificMovieModel = it
            Glide.with(this).load(Credentials.IMAGE_BASE_URL+specificMovieModel.posterPath)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(moviePosterImageView)

            //set title
            collapsingToolbar.title = specificMovieModel.title
            //set title
            movieTitleTextView.text = specificMovieModel.title
            //set ratings
            ratingBar.rating = (specificMovieModel.voteAverage % 5.0f).toFloat()
            //set release date
            releaseDateTextView.text = specificMovieModel.releaseDate

            //set overview
            synopsisTextView.text = specificMovieModel.overview

        })





    }


}