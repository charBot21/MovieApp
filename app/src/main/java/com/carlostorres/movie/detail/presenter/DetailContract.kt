package com.carlostorres.movie.detail.presenter

import com.carlostorres.movie.detail.data.model.DetailResponse

interface DetailContract {

    interface DetailView {
        fun showDetailData(title: String, image: String,
                           overview: String, homepage: String?,
                           showShare: Int)
        fun showErrorMessage(message: String)
    }

    interface DetailPresenter {
        fun getRequestDetailMovie( idMovie: String )
    }

    interface DetailData {
        fun onSuccessDataResponse( movieDetail: DetailResponse)
        fun onErrorDataResponse( message: String )
    }
}