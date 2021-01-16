package com.carlostorres.movie.home.data.local.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.carlostorres.movie.home.data.local.dao.MovieDao
import com.carlostorres.movie.home.data.local.entity.Movies

class MoviesRepository( private val moviesDao: MovieDao ) {

    val allMovies: LiveData<List<Movies>> = moviesDao.getMovies()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertMovie(movies: Movies) {
        moviesDao.insertMovie(movies)
    }
}