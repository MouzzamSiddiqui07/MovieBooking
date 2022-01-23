package com.example.moviebooking.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.moviebooking.entity.MovieLike
import com.example.moviebooking.R
import com.example.moviebooking.utils.Credentials
import com.example.moviebooking.view.SpecificMovie

class FavMovieAdapter(private val context : Context , private val movieList : List<MovieLike>) : RecyclerView.Adapter<FavMovieAdapter.MyHolder>()
{


    class MyHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {
        val posterImageView = itemView.findViewById<ImageView>(R.id.moviePosterImageView)
        val titleTextView = itemView.findViewById<TextView>(R.id.titleTextView)
        val releaseDateTextView = itemView.findViewById<TextView>(R.id.releaseDateTextView)
        val synopsisTextView = itemView.findViewById<TextView>(R.id.synopsisTextView)
        val RatingBar = itemView.findViewById<RatingBar>(R.id.ratingBar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.fav_movie_item,parent,false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val movieLike   = movieList[position]
        holder.releaseDateTextView.text = movieLike.releaseDate
        holder.synopsisTextView.text = movieLike.overview
        holder.titleTextView.text = movieLike.title
        holder.RatingBar.rating = movieLike.rating.toFloat()

        //set the image
        Glide.with(context).load(Credentials.IMAGE_BASE_URL + movieLike.posterPath)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.posterImageView)

        //when click on fav movies
        holder.itemView.setOnClickListener{
            val intent = Intent(context, SpecificMovie::class.java)
            intent.putExtra("movie_id",movieLike.movieId)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}