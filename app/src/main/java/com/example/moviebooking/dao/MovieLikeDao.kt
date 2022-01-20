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

    @Query("Update MovieLike set isLike = :isLike where movieId=:movieId")
    suspend fun updateMovie(movieId : Int , isLike : Boolean)


    @Delete
    suspend fun deleteMovieLike(movieLike: MovieLike)

    @Query("select * from MovieLike where isLike=1")
    fun getMovieLike() : LiveData<List<MovieLike>>

    @Query("select * from MovieLike where movieId = :id")
    fun getMovieRow(id : Int) : LiveData<MovieLike>
}