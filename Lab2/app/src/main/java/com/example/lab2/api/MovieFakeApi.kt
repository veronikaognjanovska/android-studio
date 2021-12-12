package com.example.lab2.api

import com.example.lab2.models.Movie

class MovieFakeApi {

    private lateinit var movies: MutableList<Movie>

    companion object {
        private lateinit var instance: MovieFakeApi

        fun getInstance(): MovieFakeApi {
            if (!this::instance.isInitialized) {
                instance = MovieFakeApi()
                this.setMoviesData()
            }
            return instance
        }

        private fun setMoviesData() {
            this.instance.movies = dummyData
        }
    }

    fun getMovies(): MutableList<Movie> {
        return this.movies
    }

    fun addNewMovie(movie: Movie): Unit {
        this.movies.add(movie)
    }

}