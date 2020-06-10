package com.example.themovies.presentation.discover

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.themovies.R
import com.example.themovies.domain.entities.Movie
import com.example.themovies.utils.BASE_IMAGE_URL
import kotlinx.android.synthetic.main.item_poster.view.*

class PopularThrillerAdapter(private val onItemClick: (movies: Movie) -> Unit) :
    RecyclerView.Adapter<PopularThrillerAdapter.ViewHolder>() {

    private val movieList = mutableListOf<Movie>()

    fun addItems(movies: List<Movie>) {
        movieList.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_poster, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movieList[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener { onItemClick(movie) }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(moves: Movie) {
            Glide.with(itemView)
                .load(BASE_IMAGE_URL + moves.posterPath)
                .into(itemView.img_poster)

            itemView.tv_title_poster.text = moves.title
            itemView.tv_voteAverage_poster.text = moves.voteAverage.toString()
        }
    }

}