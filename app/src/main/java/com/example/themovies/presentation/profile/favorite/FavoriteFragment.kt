package com.example.themovies.presentation.profile.favorite

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.themovies.R
import com.example.themovies.data.source.local.SharedPreference
import com.example.themovies.domain.entities.Movie
import com.example.themovies.utils.KEY_SESSION
import com.example.themovies.utils.hide
import com.example.themovies.utils.show
import com.example.themovies.utils.showToast
import com.example.themovies.utils.vo.Status
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_favorite.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class FavoriteFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private lateinit var viewModel: FavoriteViewModel

    private lateinit var preference: SharedPreference

    private val favoriteMoviesAdapter = FavoriteMoviesAdapter {movie ->
        showToast(movie.title)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        preference = SharedPreference(requireContext())

        viewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]
        observeViewModel()

    }

    private fun observeViewModel() {
        viewModel.getFavoriteMovies(preference.getString(KEY_SESSION)!!).observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> showLoading()
                Status.SUCCESS -> {
                    hideLoading()
                    showFavoriteMovies(it.data)
                }
                Status.ERROR -> hideLoading()
            }
        })
    }

    private fun showFavoriteMovies(favMovies: List<Movie>?) {
        favoriteMoviesAdapter.addItems(favMovies!!)

        rv_favorite.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = favoriteMoviesAdapter
        }
    }

    private fun showLoading() {
        progress_bar.show()
    }

    private fun hideLoading() {
        progress_bar.hide()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

}
