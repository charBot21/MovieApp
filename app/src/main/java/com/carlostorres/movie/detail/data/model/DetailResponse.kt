package com.carlostorres.movie.detail.data.model

import com.google.gson.annotations.SerializedName

data class DetailResponse(

    val id: Long?,
    val homepage: String?,

    @SerializedName("original_title")
    val originalTitle: String?,

    val overview: String?,

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("release_date")
    val releaseDate: String?,

    @SerializedName("title")
    val title: String?

)
