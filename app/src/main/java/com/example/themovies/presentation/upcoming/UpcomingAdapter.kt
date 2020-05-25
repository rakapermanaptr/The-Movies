package com.example.themovies.presentation.upcoming

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.themovies.R
import com.example.themovies.domain.entities.Movie
import com.example.themovies.utils.BASE_IMAGE_URL
import com.example.themovies.utils.releaseDate
import kotlinx.android.synthetic.main.item_poster_grid.view.*

class UpcomingAdapter(private val onItemClick: (movie: Movie) -> Unit) :
    RecyclerView.Adapter<UpcomingAdapter.ViewHolder>() {

    private val upcomingList = mutableListOf<Movie>()

    fun addItems(movies: List<Movie>) {
        upcomingList.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_poster_grid, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = upcomingList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = upcomingList[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener { onItemClick(movie) }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: Movie) {
            Glide.with(itemView)
                .load(BASE_IMAGE_URL + movie.posterPath)
                .into(itemView.img_poster)

            itemView.tv_title_poster.text = movie.title
            itemView.tv_voteAverage_poster.text = releaseDate(movie.releaseDate!!)

            Log.d("NowPlayingAdapter", "title: ${movie.title}")
        }
    }

}