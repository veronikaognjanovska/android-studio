package com.example.retrofitapp.database.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.retrofitapp.models.Data
import com.example.retrofitapp.models.Playlist

abstract class PlaylistDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertPlaylist(playlist: Playlist)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertTracks(data: MutableList<Data>)

    fun insertPlaylistWithTracks(playlist: Playlist, tracks: MutableList<Data>) {
        try {
            insertPlaylist(playlist)
            for (track in tracks) {
                track.playlistId = playlist.id
            }
            insertTracks(tracks)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}