package com.example.retrofitapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Data {
    // Song
    @PrimaryKey
    var id: Long
    var title: String

    var playlistId: Long
        get() = playlistId
        set(value) {
            playlistId = value
        }

    constructor(id: Long, title: String) {
        this.id = id
        this.title = title
    }

}