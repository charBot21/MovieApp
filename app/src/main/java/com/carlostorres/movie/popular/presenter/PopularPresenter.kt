package com.carlostorres.movie.popular.presenter

import com.carlostorres.movie.home.data.model.TopResults
import com.carlostorres.movie.home.manager.TopRated
import com.carlostorres.movie.home.presenter.TopRatedContract

class PopularPresenter(
    val popularView: PopularContract.PopularView,
    val topRated: TopRated
): PopularContract.PopularPresenter, TopRatedContract.TopRatedData {

    override fun getRequestPopularMovies() {
        popularView.showLoading()
        topRated.getMoviesPopular(this)
    }

    override fun onSuccessDataResponse(topRatedMovies: List<TopResults>) {
        popularView.hideLoading()
        popularView.loadPopular(topRatedMovies)
    }

    override fun onErrorDataResponse(message: String) {
        popularView.hideLoading()
        popularView.showErrorMessage(message)
    }


}