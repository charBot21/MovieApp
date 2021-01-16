package com.carlostorres.movie.popular.presenter

import com.carlostorres.movie.home.data.model.TopResults

interface PopularContract {

    interface PopularView {
        fun showLoading()
        fun hideLoading()
        fun loadPopular( list: List<TopResults> )
        fun showErrorMessage( message: String )
    }

    interface PopularPresenter {
        fun getRequestPopularMovies()
    }
}