package com.carlostorres.movie.home.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.carlostorres.movie.home.data.local.entity.Movies

@Dao
interface MovieDao {

    @Query("SELECT * from movies_table")
    fun getMovies(): LiveData<List<Movies>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movies: Movies)
}