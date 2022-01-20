package com.example.moviebooking.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.moviebooking.Entity.MovieLike

@Dao
interface MovieLikeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovieLike(movieLike : MovieLike)

    @Update
    suspend fun updateMovieLike(movieLike : MovieLike)


    @Delete
    suspend fun deleteMovieLike(movieLike: MovieLike)

    @Query("select * from MovieLike")
    fun getMovieLike() : LiveData<List<MovieLike>>

    @Query("select * from MovieLike where movieId = :id")
    fun getMovieRow(id : Int) : LiveData<MovieLike>
}