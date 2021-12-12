package com.example.lab2.api

import com.example.lab2.models.Actor
import com.example.lab2.models.Director
import com.example.lab2.models.Movie

var dummyData = mutableListOf(
    Movie(
        1L, "title 1", "description",
        Director(2L, "Director name 2", "Director surname", 1980),
        mutableListOf(
            Actor(3L, "Actor name 3", "Actor surname", 1990),
            Actor(4L, "Actor name 4", "Actor surname", 1991),
            Actor(4L, "Actor name 5", "Actor surname", 1991),
            Actor(4L, "Actor name 6", "Actor surname", 1991),
            Actor(4L, "Actor name 7", "Actor surname", 1991),
            Actor(4L, "Actor name 8", "Actor surname", 1991),
        )
    ),
    Movie(
        5L, "title 5", "description",
        Director(6L, "Director name 6", "Director surname", 1980),
        mutableListOf(
            Actor(7L, "Actor name 7", "Actor surname", 1990),
            Actor(8L, "Actor name 8", "Actor surname", 1991)
        )
    ),
    Movie(
        1L, "title 1", "description",
        Director(2L, "Director name 2", "Director surname", 1980),
        mutableListOf(
            Actor(3L, "Actor name 3", "Actor surname", 1990),
            Actor(4L, "Actor name 4", "Actor surname", 1991)
        )
    ),
    Movie(
        5L, "title 5", "description",
        Director(6L, "Director name 6", "Director surname", 1980),
        mutableListOf(
            Actor(7L, "Actor name 7", "Actor surname", 1990),
            Actor(8L, "Actor name 8", "Actor surname", 1991)
        )
    ),
    Movie(
        1L, "title 1", "description",
        Director(2L, "Director name 2", "Director surname", 1980),
        mutableListOf(
            Actor(3L, "Actor name 3", "Actor surname", 1990),
            Actor(4L, "Actor name 4", "Actor surname", 1991)
        )
    ),
    Movie(
        5L, "title 5", "description",
        Director(6L, "Director name 6", "Director surname", 1980),
        mutableListOf(
            Actor(7L, "Actor name 7", "Actor surname", 1990),
            Actor(8L, "Actor name 8", "Actor surname", 1991)
        )
    ),
    Movie(
        1L, "title 1", "description",
        Director(2L, "Director name 2", "Director surname", 1980),
        mutableListOf(
            Actor(3L, "Actor name 3", "Actor surname", 1990),
            Actor(4L, "Actor name 4", "Actor surname", 1991)
        )
    ),
    Movie(
        5L, "title 5", "description",
        Director(6L, "Director name 6", "Director surname", 1980),
        mutableListOf(
            Actor(7L, "Actor name 7", "Actor surname", 1990),
            Actor(8L, "Actor name 8", "Actor surname", 1991)
        )
    )
)