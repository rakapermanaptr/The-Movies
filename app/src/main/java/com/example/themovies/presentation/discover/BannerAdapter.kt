package com.example.themovies.presentation.discover

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.themovies.R
import com.example.themovies.domain.entities.Movie
import com.example.themovies.utils.BASE_IMAGE_URL

class BannerAdapter(private val context: Context, private var popularMovies: List<Movie>) :
    PagerAdapter() {

    override fun isViewFromObject(view: View, o: Any): Boolean = view == o

    override fun getCount(): Int = popularMovies.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        try {
            val movie: Movie = popularMovies[position]
            val view = LayoutInflater.from(context).inflate(R.layout.item_banner, null)
            val imageBanner = view.findViewById<ImageView>(R.id.img_backdrop)
            Glide.with(context)
                .load(BASE_IMAGE_URL + movie.backdropPath)
                .into(imageBanner)

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