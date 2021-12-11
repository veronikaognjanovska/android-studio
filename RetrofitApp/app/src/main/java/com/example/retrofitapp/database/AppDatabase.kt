package com.example.retrofitapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.retrofitapp.database.dao.PlaylistDao
import com.example.retrofitapp.models.Data
import com.example.retrofitapp.models.Playlist

// connection to database
@Database(entities = [Playlist::class, Data::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun playlistDao(): PlaylistDao

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
            AppDatabase::class.java, "playlist.db"
        ).build()
    }
}