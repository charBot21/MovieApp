package com.carlostorres.movie.login.data

import android.content.Context
import android.content.SharedPreferences

object PreferencesProvider {
    var IS_USER_LOGIN = "isUserLogin"
    var IS_USERNAME = "Username"
    var sharedPreferences: SharedPreferences? = null

    fun providePreferences(): SharedPreferences? {
        return sharedPreferences
    }

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences("Login", Context.MODE_PRIVATE)
    }
}