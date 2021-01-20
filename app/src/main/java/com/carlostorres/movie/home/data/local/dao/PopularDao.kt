package com.carlostorres.movie.home.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.carlostorres.movie.home.data.local.entity.Movies

@Dao
interface PopularDao {

    @Query("SELECT * from movies_table")
    fun allPopular(): LiveData<List<Movies>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPopularMovie(popular: Movies)
}