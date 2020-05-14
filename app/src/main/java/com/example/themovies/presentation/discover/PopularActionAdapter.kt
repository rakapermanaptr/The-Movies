package com.example.themovies.presentation.discover

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.themovies.R
import com.example.themovies.domain.entities.Movie
import com.example.themovies.domain.entities.TvShow
import com.example.themovies.utils.BASE_IMAGE_URL
import kotlinx.android.synthetic.main.item_poster.view.*

class PopularActionAdapter : RecyclerView.Adapter<PopularActionAdapter.ViewHolder>() {

    private val popularActionList = mutableListOf<Movie>()

    fun addItems(movies: List<Movie>) {
        popularActionList.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_poster, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = popularActionList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = popularActionList[position]
        holder.bind(movie)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: Movie) {
            Glide.with(itemView)
                .load(BASE_IMAGE_URL + movie.posterPath)
                .into(itemView.img_poster)

            itemView.tv_title_poster.text = movie.title
            itemView.tv_voteAverage_poster.text = movie.voteAverage.toString()

            Log.d("PopularActionAdapter", "title: ${movie.title}")
        }
    }

}