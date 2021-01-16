package com.carlostorres.movie.home.data.model

import com.google.gson.annotations.SerializedName

data class RatedResponse (

    var page: Int,

    @SerializedName("total_results")
    var totalResults: Int,

    @SerializedName("total_pages")
    var totalPages: Int,

    var results: List<TopResults>
    )