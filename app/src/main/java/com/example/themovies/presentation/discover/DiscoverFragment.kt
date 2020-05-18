package com.example.themovies.presentation.discover

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.themovies.R
import com.example.themovies.domain.entities.Movie
import com.example.themovies.domain.entities.TvShow
import com.example.themovies.utils.*
import com.example.themovies.utils.vo.Status
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_discover.*
import javax.inject.Inject


class DiscoverFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private lateinit var viewModel: DiscoverViewModel

    private val popularTvShowsAdapter = PopularTvShowsAdapter { tvShow ->
        NavigationUtils.navigateToDetailActivity(requireContext(), tvShow.id, TV_SHOW)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this, factory)[DiscoverViewModel::class.java]
        observeViewModel()

    }

    private fun observeViewModel() {
        viewModel.getPopularMovies().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> showLoading()
                Status.SUCCESS -> {
                    hideLoading()
                    showPopularMovies(it.data)
                }
                Status.ERROR -> hideLoading()
            }
        })

        viewModel.getPopularTvShows().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> showLoading()
                Status.SUCCESS -> {
                    hideLoading()
                    showPopularTvShows(it.data)
                }
                Status.ERROR -> hideLoading()
            }
        })

        viewModel.getPopularDrama().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> showLoading()
                Status.SUCCESS -> {
                    hideLoading()
                    showPopularDrama(it.data)
                }
                Status.ERROR -> hideLoading()
            }
        })

        viewModel.getPopularAction().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> showLoading()
                Status.SUCCESS -> {
                    hideLoading()
                    showPopularAction(it.data)
                }
                Status.ERROR -> hideLoading()
            }
        })
    }

    private fun showPopularMovies(movies: List<Movie>?) {
        val movieList: MutableList<Movie> = mutableListOf()
        movieList.clear()
        movieList.addAll(movies!!)

        val bannerAdapter = BannerAdapter(requireContext(), movieList)
        viewPagerBanner.pageMargin = 16
        viewPagerBanner.adapter = bannerAdapter
    }

    private fun showPopularTvShows(tvShows: List<TvShow>?) {
        tv_header_mostPopularTvShows.text = getString(R.string.most_popular_tv_shows)

        popularTvShowsAdapter.addItems(tvShows!!)
        rv_popularTvShows.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            adapter = popularTvShowsAdapter
        }
    }

    private fun showPopularDrama(popularDramaList: List<TvShow>?) {
        tv_header_mostPopularDrama.text = getString(R.string.most_popular_drama)

        val popularDramaAdapter = PopularDramaAdapter()
        popularDramaAdapter.addItems(popularDramaList!!)

        rv_popularDrama.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            adapter = popularDramaAdapter
        }
    }

    private fun showPopularAction(popularActionList: List<Movie>?) {
        tv_header_mostPopularAction.text = getString(R.string.most_popular_action)

        val popularActionAdapter = PopularActionAdapter()
        popularActionAdapter.addItems(popularActionList!!)

        rv_popularAction.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            adapter = popularActionAdapter
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
        return inflater.inflate(R.layout.fragment_discover, container, false)
    }

}
