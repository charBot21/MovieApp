package com.carlostorres.movie.utils

object Environment {
    const val URL_BASE = "https://api.themoviedb.org/3/"

    const val IMAGE_ENDPOINT = "https://image.tmdb.org/t/p/w200"

    // Movies
    const val MOVIE_URL_BASE = "https://api.themoviedb.org/3/movie/"
    const val GET_TOP_RATED_ENDPOINT = "https://api.themoviedb.org/3/movie/top_rated?api_key=568ff4df19633325b978ea1b75fe2290&language=en-US&page=1"
    const val SEARCH_ENDPOINT = "https://api.themoviedb.org/3/search/movie?"
    const val GET_POPULAR_ENDPOINT = "https://api.themoviedb.org/3/movie/popular?api_key=568ff4df19633325b978ea1b75fe2290&language=en-US&page=1"
    const val GET_UPCOMING_ENDPOINT = "https://api.themoviedb.org/3/movie/upcoming?"
}