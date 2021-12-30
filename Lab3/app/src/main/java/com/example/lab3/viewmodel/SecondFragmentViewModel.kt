package com.example.lab3.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.lab3.database.AppDatabase
import com.example.lab3.models.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SecondFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private var app: Application = application
    private val database: AppDatabase = AppDatabase.getInstance(application)
    private var movieMutableLiveData: MutableLiveData<Movie> =
        MutableLiveData<Movie>()

    fun loadDataFromDatabase(imdbID: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val movie = database.movieDao().getMovie(imdbID)
            withContext(Dispatchers.Main) {
                movieMutableLiveData.value = movie
            }
        }
    }

    fun getMovieMutableLiveData(): MutableLiveData<Movie> {
        return movieMutableLiveData
    }

}