package com.carlostorres.movie.detail.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.bumptech.glide.Glide
import com.carlostorres.movie.R
import com.carlostorres.movie.detail.presenter.DetailContract
import com.carlostorres.movie.detail.presenter.DetailPresenter
import com.carlostorres.movie.home.manager.TopRatedManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), DetailContract.DetailView, View.OnClickListener {

    private lateinit var presenter: DetailContract.DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        presenter = DetailPresenter(this, TopRatedManager())
        intent.getStringExtra("idMovie")?.let { presenter.getRequestDetailMovie(it) }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("RestrictedApi")
    override fun showDetailData(title: String, image: String,
                                overview: String, homepage: String?,
                                showShare: Int) {
        collapsingDetail.title = title

        fabShare.visibility = showShare
        fabShare.tag = homepage
        fabShare.setOnClickListener(this)

        Glide.with(this).load(image).into(imageViewPoster)
        textviewOverview.text = overview
    }

    override fun showErrorMessage(message: String) {
        Snackbar.make(
            coordinatorDetail,
            message,
            Snackbar.LENGTH_INDEFINITE).setAction("Reintentar") {
            intent.getStringExtra("idMovie")?.let { it1 -> presenter.getRequestDetailMovie(it1) }
        }.show()

    }

    companion object {
        fun launch(context: Context, idMovie: String = "") {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("idMovie", idMovie)
            context.startActivity(intent)
        }
    }

    override fun onClick(v: View?) {

        when( v?.id ) {
            fabShare.id -> {
                val intent = Intent()
                intent.action = Intent.ACTION_VIEW
                intent.data = Uri.parse(v.tag.toString())
                startActivity(Intent.createChooser(intent, "Abrir con..."))
            }
        }
    }
}