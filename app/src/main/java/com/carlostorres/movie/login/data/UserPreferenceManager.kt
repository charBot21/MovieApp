package com.carlostorres.movie.login.data

import android.content.SharedPreferences
import com.carlostorres.movie.login.data.PreferencesProvider.IS_USERNAME
import com.carlostorres.movie.login.data.PreferencesProvider.IS_USER_LOGIN

class UserPreferenceManager: UserPreferences {

    var sharedPreferences: SharedPreferences? = null

    init {
        sharedPreferences = PreferencesProvider.providePreferences()
    }

    override fun setUserLoginSuccess(status: Boolean) {
        sharedPreferences?.edit()?.putBoolean(IS_USER_LOGIN, status)?.apply()
    }

    override fun setUserNameSuccess(name: String) {
        sharedPreferences?.edit()?.putString(IS_USERNAME, name)?.apply()
    }

    override fun isUserLogin(): Boolean {
        return sharedPreferences?.getBoolean(IS_USER_LOGIN, false)!!
    }

    override fun clearUserSession() {
        sharedPreferences?.edit()?.clear()?.apply()
    }
}