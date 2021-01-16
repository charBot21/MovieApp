package com.carlostorres.movie.home.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import com.carlostorres.movie.R
import com.carlostorres.movie.popular.view.PopularFragment
import com.carlostorres.movie.upcoming.view.UpcomingFragment
import com.carlostorres.movie.utils.NetworkStateUtil
import com.carlostorres.movie.utils.alertDialog
import com.carlostorres.movie.utils.toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.content_home.view.*

class HomeActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private val networkStatus = NetworkStateUtil(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        home_content.mainBottomNavigationView.setOnNavigationItemSelectedListener(this)

        supportFragmentManager
            .beginTransaction()
            .replace(home_content.fragmentContainer.id, TopRatedFragment())
            .commit()

        networkStatus.status = { isAvailable, type ->
            runOnUiThread {
                when ( isAvailable ) {
                    true -> {
                        toast(getString(R.string.connected_network))
                    }
                    false -> {
                        alertDialog(getString(R.string.title_network),
                            getString(R.string.desc_network),
                            getString(R.string.connection))
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        networkStatus.register()
    }

    override fun onStop() {
        super.onStop()
        networkStatus.unregister()
    }

    companion object {
        fun launch( context: Context) {
            context.startActivity(Intent(context, HomeActivity::class.java))
        }
    }

    override fun onNavigationItemSelected( menuItem: MenuItem): Boolean {
        when ( menuItem.itemId ) {
            R.id.action_popular -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(home_content.fragmentContainer.id, PopularFragment())
                    .commit()
            }
            R.id.action_top_rated -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(home_content.fragmentContainer.id, TopRatedFragment())
                    .commit()
            }
            R.id.action_upcoming -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(home_content.fragmentContainer.id, UpcomingFragment())
                    .commit()
            }

        }
        return false
    }
}