package com.carlostorres.movie.home.presenter

import androidx.lifecycle.MutableLiveData
import com.carlostorres.movie.home.data.local.entity.Movies
import com.carlostorres.movie.home.data.model.TopResults
import com.carlostorres.movie.home.manager.TopRated

class TopRatedPresenter (
    private val topRatedView: TopRatedContract.TopRatedView,
    private val topRated: TopRated
): TopRatedContract.TopRatedPresenter, TopRatedContract.TopRatedData {

    private val allMovies: MutableLiveData<List<Movies>> = MutableLiveData()

    init {
        getRequestTopMovies()
    }

    override fun getRequestTopMovies() {
        topRatedView.showLoading()
        topRated.getMoviesTopRated(this)

    }

    override fun getSearchMoviesWithTitle(title: String) {
        topRatedView.showLoading()
        topRated.getMoviesSearch(title, this)
    }

    override fun onSuccessDataResponse(topRatedMovies: List<TopResults>) {
        topRatedView.hideLoading()
        topRatedView.showTopRatedMovies(topRatedMovies)
    }

    override fun onErrorDataResponse(message: String) {
        topRatedView.hideLoading()
        topRatedView.showErrorData(message)
    }

}