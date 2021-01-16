package com.carlostorres.movie.login.resources

import android.content.Context
import com.carlostorres.movie.R
import java.lang.ref.WeakReference

class LoginResources( context: Context ) {

    private val context = WeakReference<Context>(context)

    val userEmpty: String by lazy {
        checkNotNull(this.context)
        this.context.get()!!.getString(R.string.login_email_empty)
    }

    val userInvalid: String by lazy {
        checkNotNull(this.context)
        this.context.get()!!.getString(R.string.login_email_invalid)
    }

    val passwordEmpty: String by lazy {
        checkNotNull(this.context)
        this.context.get()!!.getString(R.string.login_password_empty)
    }

    val successLogin: String by lazy {
        checkNotNull(this.context)
        this.context.get()!!.getString(R.string.login_success)
    }

    val welcomeBack: String by lazy {
        checkNotNull(this.context)
        this.context.get()!!.getString(R.string.login_welcome_back)
    }
}