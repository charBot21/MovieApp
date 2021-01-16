package com.carlostorres.movie.login.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.carlostorres.movie.R
import com.carlostorres.movie.home.view.HomeActivity
import com.carlostorres.movie.login.data.UserPreferenceManager
import com.carlostorres.movie.login.presenter.LoginContract
import com.carlostorres.movie.login.presenter.LoginPresenter
import com.carlostorres.movie.login.resources.LoginResources
import com.carlostorres.movie.utils.alertDialog
import com.carlostorres.movie.utils.hide
import com.carlostorres.movie.utils.show
import com.carlostorres.movie.utils.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginContract.LoginView {

    private lateinit var loginPresenter: LoginContract.LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        loginPresenter = LoginPresenter(
            this,
            LoginResources(this),
            UserPreferenceManager()
        )

        btnLogin.setOnClickListener {
            loginPresenter.login(
                txtInputEditUser.text.toString(),
                txtInputEditPassword.text.toString()
            )
        }
    }

    override fun showLoading() {
        progressBarLogin.show()
    }

    override fun hideLoading() {
        progressBarLogin.hide()
    }

    override fun onSuccessLogin(message: String) {
        toast(message)
        HomeActivity.launch(this)
    }

    override fun showErrorMessage(message: String) {
        val title =  getString(R.string.app_name)
        val btnLabel = getString(R.string.btn_ok)
        alertDialog(title, message, btnLabel)
    }
}