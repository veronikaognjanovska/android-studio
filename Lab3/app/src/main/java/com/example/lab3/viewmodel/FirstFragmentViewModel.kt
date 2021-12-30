package com.example.lab3.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.lab3.R
import com.example.lab3.api.OmdbApi
import com.example.lab3.api.OmdbApiClient
import com.example.lab3.database.AppDatabase
import com.example.lab3.models.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FirstFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private var omdbApiClient: OmdbApi
    private var app: Application
    private val database: AppDatabase = AppDatabase.getInstance(application)
    private var movieMutableLiveData: MutableLiveData<Movie>
    private var moviesListMutableLiveData: MutableLiveData<MutableList<Movie>>

    init {
        this.omdbApiClient = OmdbApiClient.getOmdbApi()!!
        this.app = application
        this.movieMutableLiveData = MutableLiveData<Movie>()
        this.moviesListMutableLiveData = MutableLiveData(mutableListOf<Movie>())
        this.getMoviesFromDatabase()
    }

    fun searchMovieByTitle(title: String) {
        this.omdbApiClient.getMovieByTitle(title, app.resources.getString(R.string.api_key))
            .enqueue(object : Callback<Movie> {
                override fun onResponse(call: Call<Movie>?, response: Response<Movie>) {
                    if (response.body() != null) {
                        var currentMovie: Movie = response.body()
                        saveMovieInDatabase(currentMovie)
                        movieMutableLiveData.value = currentMovie
                        moviesListMutableLiveData.value?.add(currentMovie)
                    } else {
                        Toast.makeText(app, "Error!", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<Movie>?, t: Throwable?) {
                    Toast.makeText(app, "Error!", Toast.LENGTH_LONG).show()
                }

            })
    }

    fun getMovieMutableLiveData(): MutableLiveData<Movie> {
        return this.movieMutableLiveData
    }

    fun getMoviesListMutableLiveData(): MutableLiveData<MutableList<Movie>> {
        return this.moviesListMutableLiveData
    }

    fun saveMovieInDatabase(currentMovie: Movie) {
        CoroutineScope(Dispatchers.IO).launch {
            database.movieDao().insertMovie(currentMovie)
        }
    }

    fun getMoviesFromDatabase() {
        CoroutineScope(Dispatchers.IO).launch {
            var list = database.movieDao().getMovies()
            withContext(Dispatchers.Main) {
                moviesListMutableLiveData.value = list
            }
        }
    }


}