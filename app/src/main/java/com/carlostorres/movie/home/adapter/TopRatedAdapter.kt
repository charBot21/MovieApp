package com.carlostorres.movie.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.carlostorres.movie.R
import com.carlostorres.movie.home.data.model.TopResults
import com.carlostorres.movie.utils.Environment.IMAGE_ENDPOINT

class TopRatedAdapter(  val topRatedList: List<TopResults>,
                        val onItemClickListener: OnItemClickListener<TopResults> ):
    RecyclerView.Adapter<TopRatedAdapter.TopViewHolder>(), Filterable {private var topFilterList: List<TopResults> = topRatedList

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val query = charSequence.toString().toLowerCase()
                val filterResults = Filter.FilterResults()
                filterResults.values = if ( query.isEmpty() ) {
                    topRatedList
                } else {
                    topRatedList.filter {
                        it.title.orEmpty().toLowerCase().contains(query)
                    }
                }
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                topFilterList = results?.values as List<TopResults>
                notifyDataSetChanged()
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_top_rated, parent, false) as View

        return TopViewHolder(view)
    }

    override fun getItemCount(): Int {
        return topFilterList.size
    }

    override fun onBindViewHolder(holder: TopViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(topFilterList[position])
        }

        holder.tvTitle.text = topFilterList[position].title
        holder.tvOverview.text = topFilterList[position].overview
        holder.tvDate.text = topFilterList[position].releaseDate

        Glide.with(holder.itemView.context)
            .load("${IMAGE_ENDPOINT}${topFilterList[position].poster}")
            .into(holder.ivPoster)
    }


    class TopViewHolder( view: View): RecyclerView.ViewHolder(view) {

        var tvTitle: TextView
        val tvOverview: TextView
        val ivPoster: ImageView
        val tvDate: TextView

        init {
            ivPoster = view.findViewById(R.id.ivPoster)
            tvTitle = view.findViewById(R.id.tvTitle)
            tvOverview = view.findViewById(R.id.tvOverview)
            tvDate = view.findViewById(R.id.tvDate)
        }

    }
}