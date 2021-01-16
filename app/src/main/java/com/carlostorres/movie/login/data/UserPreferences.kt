package com.carlostorres.movie.login.data

interface UserPreferences {

    fun setUserLoginSuccess( status: Boolean )
    fun setUserNameSuccess( name: String )
    fun isUserLogin(): Boolean
    fun clearUserSession()
}