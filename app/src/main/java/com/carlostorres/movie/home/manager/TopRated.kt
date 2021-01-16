package com.carlostorres.movie.home.manager

import com.carlostorres.movie.detail.presenter.DetailContract
import com.carlostorres.movie.home.presenter.TopRatedContract

interface TopRated {

    fun getMoviesTopRated(topRatedData: TopRatedContract.TopRatedData)

    fun getMoviesSearch(search: String, topRatedData: TopRatedContract.TopRatedData)

    fun getMoviesPopular(topRatedData: TopRatedContract.TopRatedData)

    fun getMoviesUpcoming(topRatedData: TopRatedContract.TopRatedData)

    fun getMovieDetail(idMovie: String, detailData: DetailContract.DetailData)
}