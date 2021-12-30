package com.example.lab3.database.dao

import androidx.room.*
import com.example.lab3.models.Movie

@Dao
abstract class MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMovie(movie: Movie)

    @Transaction
    @Query("SELECT * FROM Movie WHERE imdbID=:imdbID")
    abstract fun getMovie(imdbID: String): Movie?

    @Transaction
    @Query("SELECT * FROM Movie ")
    abstract fun getMovies(): MutableList<Movie>?
}