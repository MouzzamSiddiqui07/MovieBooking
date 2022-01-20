package com.example.moviebooking.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName="MovieLike")
data class MovieLike(

    @PrimaryKey
    val movieId : Int,
    val isLike  : Boolean
)
