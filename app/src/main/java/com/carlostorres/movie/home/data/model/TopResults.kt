package com.carlostorres.movie.home.data.model

import com.google.gson.annotations.SerializedName

class TopResults (

    var id: Int,

    @SerializedName("poster_path")
    var poster: String,

    var title: String,

    var overview: String,

    @SerializedName("release_date")
    var releaseDate: String
)