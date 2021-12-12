package com.example.lab2.models

class Movie(
    val id: Long,
    val title: String,
    val description: String,
    val director: Director,
    val actors: MutableList<Actor>
) : java.io.Serializable