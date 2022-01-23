package com.example.moviebooking.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.moviebooking.model.Result
import com.example.moviebooking.R
import com.example.moviebooking.utils.Credentials
import com.example.moviebooking.view.SpecificMovie
import com.example.moviebooking.database.MovieDatabase
import com.example.moviebooking.entity.MovieLike
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class PopularMoviesAdapter(private val context : Context, private val popularMoviesList : List<Result>,private val movieDatabase: MovieDatabase) : RecyclerView.Adapter<PopularMoviesAdapter.MyHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {


            val view = LayoutInflater.from(context).inflate(R.layout.popular_movie_item , parent,false)
            return MyHolder(view)

    }

    override fun onBindViewHolder(holder: MyHolder , position: Int) {
            val popularMovie = popularMoviesList[position]

                Glide.with(context).load(Credentials.IMAGE_BASE_URL + popularMovie.posterPath)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.ImageView)

        //set movie title
        holder.movieTitleTextView.text =  popularMovie.title
        //set release date
        holder.movieReleaseDate.text =  popularMovie.releaseDate


            //put the like data in database
         GlobalScope.launch {
             movieDatabase.movieLikeDao().insertMovieLike(MovieLike(popularMovie.id, false,"","","",0.0,popularMovie.posterPath))
         }

        //when click on specific movie poster
        holder.itemView.setOnClickListener{
            val intent = Intent(context, SpecificMovie::class.java)
            intent.putExtra("movie_id",popularMovie.id)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return popularMoviesList.size
    }

    class MyHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {
        val ImageView  =  itemView.findViewById<ImageView>(R.id.movieImageView)
        val movieTitleTextView = itemView.findViewById<TextView>(R.id.movieTitleTextView)
        val movieReleaseDate = itemView.findViewById<TextView>(R.id.movieReleaseDateTextView)
    }

}