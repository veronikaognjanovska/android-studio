package com.example.retrofitapp.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.retrofitapp.api.DeezerApi
import com.example.retrofitapp.api.DeezerApiClient
import com.example.retrofitapp.database.AppDatabase
import com.example.retrofitapp.models.Playlist
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FirstFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private var deezerApiClient: DeezerApi
    private var app: Application
    private val database: AppDatabase = AppDatabase.getInstance(application)
    private var playlistMutableLiveData: MutableLiveData<Playlist>

    init {
        this.deezerApiClient = DeezerApiClient.getDeezerApi()!!
        this.app = application
        this.playlistMutableLiveData = MutableLiveData<Playlist>()
    }

    fun searchPlaylistById(id: String) {
        this.deezerApiClient.getPlaylistById(id).enqueue(object : Callback<Playlist> {
            override fun onResponse(call: Call<Playlist>?, response: Response<Playlist>) {
                if (response.body() != null) {
                    var currentPlaylist: Playlist = response.body()
                    savePlaylistInDatabase(currentPlaylist)
                    playlistMutableLiveData.value = currentPlaylist
                } else {
                    Toast.makeText(app, "Error!", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Playlist>?, t: Throwable?) {
                Toast.makeText(app, "Error!", Toast.LENGTH_LONG).show()
            }

        })
    }

    fun getPlaylistMutableLiveData(): MutableLiveData<Playlist> {
        return this.playlistMutableLiveData
    }

    fun savePlaylistInDatabase(currentPlaylist: Playlist) {
        CoroutineScope(Dispatchers.IO).launch {
            database.playlistDao()
                .insertPlaylistWithTracks(currentPlaylist, currentPlaylist.tracks.data)
        }
    }


}