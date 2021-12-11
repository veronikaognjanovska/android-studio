package com.example.retrofitapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class DeezerApiClient {

    // static things
    companion object {
        private var deezerApi: DeezerApi? = null

        fun getDeezerApi(): DeezerApi? {
            if (this.deezerApi == null) {
                this.deezerApi = Retrofit.Builder()
                    .baseUrl("https://api.deezer.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(DeezerApi::class.java)
            }
            return this.deezerApi
        }
    }

}