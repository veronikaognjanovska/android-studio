package com.example.retrofitapp.database.dao

import androidx.room.*
import com.example.retrofitapp.database.relationship.PlaylistWithTracks
import com.example.retrofitapp.models.Data
import com.example.retrofitapp.models.Playlist

@Dao
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

    @Transaction
    @Query("SELECT * FROM Playlist WHERE id=:id")
    abstract fun getPlaylistWithTracks(id: Long): PlaylistWithTracks?
}