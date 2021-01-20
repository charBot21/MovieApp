package com.carlostorres.movie.home.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.carlostorres.movie.home.data.local.dao.PopularDao
import com.carlostorres.movie.home.data.local.entity.Movies


@Database(entities = [Movies::class], version = 1)
abstract class PopularRoomDatabase: RoomDatabase() {

    abstract fun popularDao(): PopularDao

    companion object {
        @Volatile
        private var INSTANCE: PopularRoomDatabase ?= null

        fun getDatabase(context: Context): PopularRoomDatabase {
            val tmpInstance = INSTANCE

            if ( tmpInstance != null ) {
                return tmpInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        PopularRoomDatabase::class.java,
                        "popular_database"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}