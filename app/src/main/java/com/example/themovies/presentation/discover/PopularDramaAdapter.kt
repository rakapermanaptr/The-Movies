package com.example.themovies.presentation.discover

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.themovies.R
import com.example.themovies.domain.entities.TvShow
import com.example.themovies.utils.BASE_IMAGE_URL
import kotlinx.android.synthetic.main.item_poster.view.*

class PopularDramaAdapter(private val onItemClick: (tvShow: TvShow) -> Unit) :
    RecyclerView.Adapter<PopularDramaAdapter.ViewHolder>() {

    private val tvShowList = mutableListOf<TvShow>()

    fun addItems(tvShows: List<TvShow>) {
        tvShowList.addAll(tvShows)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_poster, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = tvShowList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tvShow = tvShowList[position]
        holder.bind(tvShow)
        holder.itemView.setOnClickListener { onItemClick(tvShow) }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(tvShow: TvShow) {
            Glide.with(itemView)
                .load(BASE_IMAGE_URL + tvShow.posterPath)
                .into(itemView.img_poster)

            itemView.tv_title_poster.text = tvShow.name
            itemView.tv_voteAverage_poster.text = tvShow.voteAverage.toString()
        }
    }

}