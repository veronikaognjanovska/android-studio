package com.example.retrofitapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
// Song
class Data(
    @PrimaryKey
    var id: Long = 0,
    var title: String = "",
    var playlistId: Long = 0,
)