package com.example.retrofitapp.database.relationship

import androidx.room.Embedded
import androidx.room.Relation
import com.example.retrofitapp.models.Data
import com.example.retrofitapp.models.Playlist

class PlaylistWithTracks(
    @Embedded
    val playlist: Playlist,
    @Relation(
        parentColumn = "id",
        entityColumn = "playlistId"
    )
    val tracks: MutableList<Data>
)