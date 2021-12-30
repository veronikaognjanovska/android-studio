package com.example.lab3.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Movie(
    @PrimaryKey
    var imdbID: String = "",
    var Title: String = "",
    var Year: Int = 0,
    var Released: String = "",
    var Genre: String = "",
    var Runtime: String = "",
    var Plot: String = "",
    var Poster: String = "",
    var Language: String = "",
    var Country: String = "",
    var Director: String = "",
    var Actors: String = "",
)
