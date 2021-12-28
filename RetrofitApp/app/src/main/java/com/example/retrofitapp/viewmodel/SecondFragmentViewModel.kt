package com.example.retrofitapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.retrofitapp.database.AppDatabase
import com.example.retrofitapp.database.relationship.PlaylistWithTracks
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SecondFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private var app: Application = application
    private val database: AppDatabase = AppDatabase.getInstance(application)
    private var playlistWithTracksMutableLiveData: MutableLiveData<PlaylistWithTracks> =
        MutableLiveData<PlaylistWithTracks>()

    fun loadDataFromDatabase(id: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            val playlistWithTracks = database.playlistDao().getPlaylistWithTracks(id)
            withContext(Dispatchers.Main) {
                playlistWithTracksMutableLiveData.value = playlistWithTracks
            }
        }
    }

    fun getPlaylistWithTracksMutableLiveData(): MutableLiveData<PlaylistWithTracks> {
        return playlistWithTracksMutableLiveData
    }

}