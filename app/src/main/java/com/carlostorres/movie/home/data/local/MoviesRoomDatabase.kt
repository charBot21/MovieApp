package com.carlostorres.movie.home.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.carlostorres.movie.home.data.local.dao.MovieDao
import com.carlostorres.movie.home.data.local.entity.Movies

@Database(entities = [Movies::class], version = 1)
abstract class MoviesRoomDatabase: RoomDatabase() {

    abstract fun moviesDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: MoviesRoomDatabase ?= null

        fun getDatabase(context: Context): MoviesRoomDatabase {
            val tmpInstance = INSTANCE

            if ( tmpInstance != null ) {
                return tmpInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MoviesRoomDatabase::class.java,
                    "movies_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}