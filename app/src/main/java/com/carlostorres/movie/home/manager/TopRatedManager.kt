package com.carlostorres.movie.home.manager

import com.carlostorres.movie.detail.data.model.DetailResponse
import com.carlostorres.movie.detail.presenter.DetailContract
import com.carlostorres.movie.home.data.model.RatedResponse
import com.carlostorres.movie.home.data.network.NetworkConnection
import com.carlostorres.movie.home.presenter.TopRatedContract
import com.carlostorres.movie.utils.Environment.GET_POPULAR_ENDPOINT
import com.carlostorres.movie.utils.Environment.GET_TOP_RATED_ENDPOINT
import com.carlostorres.movie.utils.Environment.GET_UPCOMING_ENDPOINT
import com.carlostorres.movie.utils.Environment.MOVIE_URL_BASE
import com.carlostorres.movie.utils.Environment.SEARCH_ENDPOINT
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopRatedManager: TopRated {
    override fun getMoviesTopRated(topRatedData: TopRatedContract.TopRatedData) {
        NetworkConnection().getNetworkConnection()
            ?.listRatedMovies(GET_TOP_RATED_ENDPOINT)
            ?.enqueue(object: Callback<RatedResponse> {
                override fun onFailure(call: Call<RatedResponse>, t: Throwable) {
                    topRatedData.onErrorDataResponse(t.message.orEmpty())
                }

                override fun onResponse(
                    call: Call<RatedResponse>,
                    response: Response<RatedResponse>
                ) {
                    topRatedData.onSuccessDataResponse(response.body()?.results ?: listOf())
                }

            })
    }

    override fun getMoviesSearch(search: String, topRatedData: TopRatedContract.TopRatedData) {
        NetworkConnection().getNetworkConnection()
            ?.listRatedMovies("${SEARCH_ENDPOINT}" +
                    "api_key=568ff4df19633325b978ea1b75fe2290" +
                    "&language=en-US" +
                    "&query=${search.replace(" ", "+")}" +
                    "&page=1")
            ?.enqueue(object: Callback<RatedResponse> {
                override fun onFailure(call: Call<RatedResponse>, t: Throwable) {
                    topRatedData.onErrorDataResponse(t.message.orEmpty())
                }

                override fun onResponse(
                    call: Call<RatedResponse>,
                    response: Response<RatedResponse>
                ) {
                    topRatedData.onSuccessDataResponse(response.body()?.results ?: listOf())
                }

            })
    }

    override fun getMoviesPopular(topRatedData: TopRatedContract.TopRatedData) {
        NetworkConnection().getNetworkConnection()
            ?.listRatedMovies(GET_POPULAR_ENDPOINT)
            ?.enqueue(object: Callback<RatedResponse> {
                override fun onFailure(call: Call<RatedResponse>, t: Throwable) {
                    topRatedData.onErrorDataResponse(t.message.orEmpty())
                }

                override fun onResponse(
                    call: Call<RatedResponse>,
                    response: Response<RatedResponse>
                ) {
                    topRatedData.onSuccessDataResponse(response.body()?.results ?: listOf())
                }

            })

    }


    override fun getMoviesUpcoming(topRatedData: TopRatedContract.TopRatedData) {

        NetworkConnection().getNetworkConnection()
            ?.listRatedMovies("${GET_UPCOMING_ENDPOINT}" +
                    "api_key=568ff4df19633325b978ea1b75fe2290" +
                    "&language=en-US" +
                    "&page=1")
            ?.enqueue(object: Callback<RatedResponse> {
                override fun onFailure(call: Call<RatedResponse>, t: Throwable) {
                    topRatedData.onErrorDataResponse(t.message.orEmpty())
                }

                override fun onResponse(
                    call: Call<RatedResponse>,
                    response: Response<RatedResponse>
                ) {
                    topRatedData.onSuccessDataResponse(response.body()?.results ?: listOf())
                }

            })

    }

    override fun getMovieDetail(idMovie: String, detailData: DetailContract.DetailData) {
        NetworkConnection()
            .getNetworkConnection()
            ?.detailMovie("${MOVIE_URL_BASE}" +
                    "$idMovie?" +
                    "api_key=568ff4df19633325b978ea1b75fe2290" +
                    "&language=en-US")
            ?.enqueue( object: Callback<DetailResponse> {
                override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                    detailData.onErrorDataResponse(t.message.orEmpty())
                }

                override fun onResponse(
                    call: Call<DetailResponse>,
                    response: Response<DetailResponse>
                ) {
                    detailData.onSuccessDataResponse(response.body()!!)
                }
            })
    }
}