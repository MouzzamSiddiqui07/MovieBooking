package com.example.moviebooking.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviebooking.Adapter.FavMovieAdapter
import com.example.moviebooking.Database.MovieDatabase
import com.example.moviebooking.Entity.MovieLike
import com.example.moviebooking.R

class FavouritesActivity : AppCompatActivity() {


    lateinit var favRecyclerView : RecyclerView

    lateinit var movieDatabase: MovieDatabase

    lateinit var favMovieLiveData : LiveData<List<MovieLike>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourites)

        favRecyclerView = findViewById(R.id.favRecyclerView)
        favRecyclerView.layoutManager = LinearLayoutManager(this)
        movieDatabase = MovieDatabase.getDatabaseInstance(this)

        favMovieLiveData = movieDatabase.movieLikeDao().getMovieLike()

        favMovieLiveData.observe(this,{
            val favMovieAdapter = FavMovieAdapter(this , it)
            favRecyclerView.adapter = favMovieAdapter

        })











    }
}