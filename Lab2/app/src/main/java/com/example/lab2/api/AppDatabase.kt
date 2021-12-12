package com.example.lab2.api

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.lab2.api.dao.MovieDao
import com.example.lab2.models.Actor
import com.example.lab2.models.Director
import com.example.lab2.models.Movie

// connection to database
@Database(entities = [Movie::class, Director::class, Actor::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    private lateinit var instance: AppDatabase

    fun getInstance(context: Context): AppDatabase {
        if (instance == null) {
            instance = createInstance(context)
        }
        return instance
    }

    private fun createInstance(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "movie.db"
        ).build()
    }
}