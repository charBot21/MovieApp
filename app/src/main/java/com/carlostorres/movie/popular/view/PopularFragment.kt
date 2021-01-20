package com.carlostorres.movie.popular.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.carlostorres.movie.R
import com.carlostorres.movie.detail.view.DetailActivity
import com.carlostorres.movie.home.adapter.OnItemClickListener
import com.carlostorres.movie.home.adapter.TopRatedAdapter
import com.carlostorres.movie.home.data.local.PopularRoomDatabase
import com.carlostorres.movie.home.data.local.entity.Movies
import com.carlostorres.movie.home.data.model.TopResults
import com.carlostorres.movie.home.manager.TopRatedManager
import com.carlostorres.movie.popular.presenter.PopularContract
import com.carlostorres.movie.popular.presenter.PopularPresenter
import kotlinx.android.synthetic.main.fragment_popular.*

class PopularFragment : Fragment(), PopularContract.PopularView, OnItemClickListener<Movies> {

    private lateinit var database: PopularRoomDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        database = PopularRoomDatabase.getDatabase(requireContext())
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val presenter = PopularPresenter(this, TopRatedManager())
        presenter.getRequestPopularMovies()
    }

    override fun showLoading() {
        progressPopular?.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressPopular?.visibility = View.GONE
    }

    override fun loadPopular(list: List<TopResults>) {

        list.forEach {
            var popular = Movies(
                    it.id,
                    it.poster,
                    it.title,
                    it.overview,
                    it.releaseDate
            )
            database.popularDao().insertPopularMovie(popular)
        }

        database.popularDao().allPopular().observe(this, Observer { items ->
            items?.let {
                rvPopular.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                val adapter = TopRatedAdapter(items, this)
                rvPopular.adapter = adapter
            }
        })
    }

    override fun showErrorMessage(message: String) {
        Toast.makeText(activity!!, message, Toast.LENGTH_SHORT).show()
    }


    override fun onItemClick(item: Movies) {
        DetailActivity.launch(activity!!, item.id.toString())
    }


}