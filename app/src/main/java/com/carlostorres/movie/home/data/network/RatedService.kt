package com.carlostorres.movie.home.data.network

import com.carlostorres.movie.detail.data.model.DetailResponse
import com.carlostorres.movie.home.data.model.RatedResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface RatedService {

    @GET
    fun listRatedMovies(@Url url: String): Call<RatedResponse>

    @GET
    fun detailMovie(@Url url: String): Call<DetailResponse>
}