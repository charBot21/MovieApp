package com.carlostorres.movie.upcoming.presenter

import com.carlostorres.movie.home.data.model.TopResults
import com.carlostorres.movie.home.manager.TopRated
import com.carlostorres.movie.home.presenter.TopRatedContract

class UpcomingPresenter(
    val popularView: UpcomingContract.UpcomingView,
    val topRated: TopRated
): UpcomingContract.UpcomingPresenter, TopRatedContract.TopRatedData {

    override fun getRequestUpcomingMovies() {
        popularView.showLoading()
        topRated.getMoviesUpcoming(this)
    }

    override fun onSuccessDataResponse(topRatedMovies: List<TopResults>) {
        popularView.hideLoading()
        popularView.loadUpcoming(topRatedMovies)
    }

    override fun onErrorDataResponse(message: String) {
        popularView.hideLoading()
        popularView.showErrorMessage(message)
    }
}