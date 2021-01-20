package com.carlostorres.movie.home.view

import android.app.AlertDialog
import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.carlostorres.movie.R
import com.carlostorres.movie.detail.view.DetailActivity
import com.carlostorres.movie.home.adapter.OnItemClickListener
import com.carlostorres.movie.home.adapter.TopRatedAdapter
import com.carlostorres.movie.home.data.local.MoviesRoomDatabase
import com.carlostorres.movie.home.data.local.entity.Movies
import com.carlostorres.movie.home.data.model.TopResults
import com.carlostorres.movie.home.manager.TopRatedManager
import com.carlostorres.movie.home.presenter.TopRatedContract
import com.carlostorres.movie.home.presenter.TopRatedPresenter
import kotlinx.android.synthetic.main.fragment_top_rated.*

class TopRatedFragment : Fragment(), TopRatedContract.TopRatedView,
    SearchView.OnQueryTextListener, OnItemClickListener<Movies> {

    private lateinit var topRatedPresenter: TopRatedContract.TopRatedPresenter
    private lateinit var database: MoviesRoomDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        database = MoviesRoomDatabase.getDatabase(requireContext())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_top_rated, menu)

        val searchItem: MenuItem? = menu?.findItem(R.id.action_search)
        val searchManager = activity!!.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView: SearchView? = searchItem?.actionView as SearchView
        searchView?.queryHint = getString(R.string.enter_title)
        searchView?.isIconified = true
        searchView?.setOnQueryTextListener(this)
        searchView?.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        topRatedPresenter.getSearchMoviesWithTitle(query.orEmpty())
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        (rvTopRatedMovies.adapter as? TopRatedAdapter)?.filter?.filter(newText.orEmpty())
        return true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_rated, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        topRatedPresenter = TopRatedPresenter(
            this,
            TopRatedManager()
        )
    }

    override fun showLoading() {
        progressTop?.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressTop?.visibility = View.GONE

    }

    override fun showTopRatedMovies(topMovies: List<TopResults>) {

        topMovies.forEach {
            var movie = Movies(
                    it.id,
                    it.poster,
                    it.title,
                    it.overview,
                    it.releaseDate
            )
            database.moviesDao().insertMovie(movie)
        }


        database.moviesDao().getMovies().observe( this, Observer { items ->
            items?.let {
                rvTopRatedMovies.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                val adapter = TopRatedAdapter(items, this)
                rvTopRatedMovies.adapter = adapter

            }
        } )

    }

    override fun showErrorData(message: String) {
        val dialog = AlertDialog.Builder(activity!!)
        dialog.setTitle(getString(R.string.app_name))
        dialog.setMessage(message)
        dialog.setNeutralButton(getString(R.string.btn_ok), null)
        dialog.create()
        dialog.show()
    }

    override fun onItemClick(item: Movies) {
        DetailActivity.launch(activity!!, item.id.toString())
    }
}