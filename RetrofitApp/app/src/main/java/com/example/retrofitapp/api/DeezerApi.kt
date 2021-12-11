package com.example.retrofitapp.api


import com.example.retrofitapp.models.Playlist
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DeezerApi {
    @GET("playlist/{id}")
    fun getPlaylistById(@Path("id") id: String): Call<Playlist> // Call from retrofitapp.api
}