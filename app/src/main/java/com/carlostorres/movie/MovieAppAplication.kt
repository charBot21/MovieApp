package com.carlostorres.movie

import android.app.Application
import com.carlostorres.movie.login.data.PreferencesProvider

class MovieAppAplication: Application() {

    override fun onCreate() {
        super.onCreate()
        PreferencesProvider.init(this)
    }
}