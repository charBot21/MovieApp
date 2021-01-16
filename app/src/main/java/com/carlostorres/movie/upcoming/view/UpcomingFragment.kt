package com.carlostorres.movie.upcoming.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.carlostorres.movie.R
import com.carlostorres.movie.detail.view.DetailActivity
import com.carlostorres.movie.home.adapter.OnItemClickListener
import com.carlostorres.movie.home.adapter.TopRatedAdapter
import com.carlostorres.movie.home.data.model.TopResults
import com.carlostorres.movie.home.manager.TopRatedManager
import com.carlostorres.movie.upcoming.presenter.UpcomingContract
import com.carlostorres.movie.upcoming.presenter.UpcomingPresenter
import kotlinx.android.synthetic.main.fragment_upcoming.*

class UpcomingFragment : Fragment(), UpcomingContract.UpcomingView,
    SwipeRefreshLayout.OnRefreshListener, OnItemClickListener<TopResults> {

    private lateinit var presenter: UpcomingContract.UpcomingPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_upcoming, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipeUpcoming?.setOnRefreshListener(this)

        presenter = UpcomingPresenter(this, TopRatedManager())
        presenter.getRequestUpcomingMovies()
    }

    override fun showLoading() {
        progressUpcoming?.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressUpcoming?.visibility = View.GONE
    }

    override fun loadUpcoming(list: List<TopResults>) {
        swipeUpcoming?.isRefreshing = false
        rvUpcoming?.apply {
            adapter = TopRatedAdapter(list, this@UpcomingFragment)
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }
    }

    override fun showErrorMessage(message: String) {
        Toast.makeText(activity!!, message, Toast.LENGTH_SHORT).show()
    }

    override fun onItemClick(item: TopResults) {
        DetailActivity.launch(activity!!, item.id.toString())
    }

    override fun onRefresh() {
        presenter.getRequestUpcomingMovies()
    }


}