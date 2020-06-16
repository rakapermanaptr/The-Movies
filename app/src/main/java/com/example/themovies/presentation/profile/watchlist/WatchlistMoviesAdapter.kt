package com.example.themovies.presentation.profile.watchlist

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.themovies.R
import com.example.themovies.domain.entities.Movie
import com.example.themovies.utils.BASE_IMAGE_URL
import kotlinx.android.synthetic.main.item_poster_favorite.view.*

class WatchlistMoviesAdapter(private val onItemClick: (movie: Movie) -> Unit) :
    RecyclerView.Adapter<WatchlistMoviesAdapter.ViewHolder>() {

    private val favMovieList = mutableListOf<Movie>()

    fun addItems(movies: List<Movie>) {
        favMovieList.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_poster_favorite, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = favMovieList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = favMovieList[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener { onItemClick (movie) }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: Movie) {
            Glide.with(itemView)
                .load(BASE_IMAGE_URL + movie.posterPath)
                .into(itemView.img_poster)

            itemView.tv_title.text = movie.title

            Log.d("WatchlistMoviesAdapter", "title: ${movie.title}")
        }
    }

}