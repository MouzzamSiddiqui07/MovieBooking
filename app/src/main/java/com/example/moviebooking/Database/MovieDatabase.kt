package com.example.moviebooking.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moviebooking.Entity.MovieLike
import com.example.moviebooking.dao.MovieLikeDao

@Database(entities = [MovieLike :: class],version = 1)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieLikeDao() : MovieLikeDao


    companion object
    {
        @Volatile
        private var databaseInstance : MovieDatabase? = null

        fun getDatabaseInstance(context : Context) :MovieDatabase
        {
            if (databaseInstance == null) {
                synchronized(this)
                {
                    databaseInstance =
                        Room.databaseBuilder(context, MovieDatabase::class.java, "MovieDb").build()
                }
            }


            return databaseInstance!!
        }

    }
}