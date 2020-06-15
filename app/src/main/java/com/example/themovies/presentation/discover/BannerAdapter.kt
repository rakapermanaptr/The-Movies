package com.example.themovies.presentation.discover

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.themovies.R
import com.example.themovies.domain.entities.Movie
import com.example.themovies.utils.BASE_IMAGE_URL
import com.example.themovies.utils.MOVIE
import com.example.themovies.utils.NavigationUtils

class BannerAdapter(private val context: Context, private var popularMovies: List<Movie>) :
    PagerAdapter() {

    override fun isViewFromObject(view: View, o: Any): Boolean = view == o

    override fun getCount(): Int = popularMovies.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        try {
            val movie: Movie = popularMovies[position]
            val view = LayoutInflater.from(context).inflate(R.layout.item_banner, null)
            val imageBanner = view.findViewById<ImageView>(R.id.img_backdrop)
            val title = view.findViewById<TextView>(R.id.tv_title_banner)

            Glide.with(context)
                .load(BASE_IMAGE_URL + movie.backdropPath)
                .into(imageBanner)
            title.text = movie.title

            view.setOnClickListener {
                NavigationUtils.navigateToDetailActivity(context, movie.id)
            }

            container.addView(view)
            return view
        } catch (e: Exception) {
            Log.e("BannerAdapterErr", e.localizedMessage!!)
        }

        return container
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}