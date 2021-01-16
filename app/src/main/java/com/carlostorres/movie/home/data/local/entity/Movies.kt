package com.carlostorres.movie.home.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies_table")
data class Movies(
    @PrimaryKey()
    @ColumnInfo(name = "info")
    val id: Int,
    var poster: String,
    var title: String,
    var overview: String,
    var releaseDate: String
)
