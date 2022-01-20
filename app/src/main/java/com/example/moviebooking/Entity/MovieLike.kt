package com.example.moviebooking.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName="MovieLike")
data class MovieLike(

    @PrimaryKey
    val movieId : Int,
    val isLike  : Boolean,
    val overview : String,
    val title : String ,
    val releaseDate : String,
    val rating : Double ,
    val posterPath : String
)
