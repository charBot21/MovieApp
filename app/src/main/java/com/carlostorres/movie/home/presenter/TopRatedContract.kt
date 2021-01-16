package com.carlostorres.movie.home.presenter

import com.carlostorres.movie.home.data.model.TopResults

interface TopRatedContract {

    interface TopRatedView {
        fun showLoading()
        fun hideLoading()
        fun showTopRatedMovies(topMovies: List<TopResults>)
        fun showErrorData(message: String)
    }

    interface TopRatedPresenter {
        fun getRequestTopMovies()
        fun getSearchMoviesWithTitle(title: String)
    }

    interface TopRatedData {
        fun onSuccessDataResponse(topRatedMovies: List<TopResults>)
        fun onErrorDataResponse(message: String)
    }
}