package com.example.lab3.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class OmdbApiClient {

    // static things
    companion object {
        private var omdbApi: OmdbApi? = null

        fun getOmdbApi(): OmdbApi? {
            if (this.omdbApi == null) {
                this.omdbApi = Retrofit.Builder()
                    .baseUrl("http://www.omdbapi.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(OmdbApi::class.java)
            }
            return this.omdbApi
        }
    }

}