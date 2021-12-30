package com.example.lab3.api

import com.example.lab3.models.Movie

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface OmdbApi {
    @GET("&i={id}")
    fun getMovieById(@Path("id") id: String): Call<Movie>

    @GET("/")
    fun getMovieByTitle(@Query("t") title: String, @Query("apikey") apikey: String): Call<Movie>


//    @GET("&s={title}&plot=short")
//    fun getMoviesByTitle(@Path("title") title: String): Call<Movie>
}