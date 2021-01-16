package com.carlostorres.movie.upcoming.presenter

import com.carlostorres.movie.home.data.model.TopResults

interface UpcomingContract {

    interface UpcomingView {
        fun showLoading()
        fun hideLoading()
        fun loadUpcoming( list: List<TopResults> )
        fun showErrorMessage( message: String )
    }

    interface UpcomingPresenter {
        fun getRequestUpcomingMovies()
    }
}