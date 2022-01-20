package com.example.moviebooking.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviebooking.Entity.MovieLike
import com.example.moviebooking.dao.MovieLikeDao

@Database(entities = [MovieLike :: class],version = 1)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieLikeDao() : MovieLikeDao
}