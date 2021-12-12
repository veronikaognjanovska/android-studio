package com.example.lab2.api.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.lab2.models.Actor
import com.example.lab2.models.Director
import com.example.lab2.models.Movie

abstract class MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMovie(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertDirector(director: Director)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertActors(actors: MutableList<Actor>)

    fun insertMovieData(movie: Movie, director: Director, actors: MutableList<Actor>) {
        try {
            insertMovie(movie)
            insertDirector(director)
            insertActors(actors)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}