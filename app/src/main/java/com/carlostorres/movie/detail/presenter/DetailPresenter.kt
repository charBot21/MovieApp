package com.carlostorres.movie.detail.presenter

import com.carlostorres.movie.detail.data.model.DetailResponse
import com.carlostorres.movie.home.manager.TopRated
import com.carlostorres.movie.utils.Constants.GONE
import com.carlostorres.movie.utils.Constants.VISIBLE

class DetailPresenter(
    val detailView: DetailContract.DetailView,
    val topRated: TopRated
): DetailContract.DetailPresenter, DetailContract.DetailData {
    override fun getRequestDetailMovie(idMovie: String) {
        topRated.getMovieDetail(idMovie, this)
    }

    override fun onSuccessDataResponse(movieDetail: DetailResponse) {

        detailView.showDetailData(
            movieDetail.title.orEmpty(),
            "https://image.tmdb.org/t/p/w500${movieDetail.posterPath.orEmpty()}",
            movieDetail.overview.orEmpty(),
            movieDetail.homepage,
            if ( movieDetail.homepage.orEmpty().isNotEmpty() ) {
                VISIBLE
            } else {
                GONE
            })
    }

    override fun onErrorDataResponse(message: String) {
        detailView.showErrorMessage(message)
    }
}