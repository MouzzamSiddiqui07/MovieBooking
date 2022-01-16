package com.example.moviebooking.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.moviebooking.Model.Result
import com.example.moviebooking.R
import com.example.moviebooking.Utils.Credentials
import com.example.moviebooking.View.MainActivity
import com.example.moviebooking.View.SpecificMovie
import com.github.ybq.android.spinkit.SpinKitView
import android.app.Activity




class PopularMoviesAdapter(private val context : Context, private val popularMoviesList : List<Result>) : RecyclerView.Adapter<PopularMoviesAdapter.MyHolder>() {



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


        //when click on specific movie poster
        holder.itemView.setOnClickListener{
            context.startActivity(Intent(context, SpecificMovie::class.java))
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