package com.example.themovies.presentation.upcoming

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager

import com.example.themovies.R
import com.example.themovies.domain.entities.Movie
import com.example.themovies.utils.MOVIE
import com.example.themovies.utils.NavigationUtils
import com.example.themovies.utils.hide
import com.example.themovies.utils.show
import com.example.themovies.utils.vo.Status
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_upcoming.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class UpcomingFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private lateinit var viewModel: UpcomingViewModel

    private val upcomingAdapter = UpcomingAdapter { movie ->
        NavigationUtils.navigateToDetailActivity(requireContext(), movie.id, MOVIE)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this, factory)[UpcomingViewModel::class.java]
        observeViewModel()

    }

    private fun observeViewModel() {
        viewModel.getUpcoming().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> showLoading()
                Status.SUCCESS -> {
                    hideLoading()
                    showUpcoming(it.data)
                }
                Status.ERROR -> hideLoading()
            }
        })
    }

    private fun showUpcoming(upcomingList: List<Movie>?) {
        upcomingAdapter.addItems(upcomingList!!)
        rv_upcoming.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = upcomingAdapter
        }
    }

    private fun showLoading() {
        progress_bar.show()
        rv_upcoming.hide()
    }

    private fun hideLoading() {
        progress_bar.hide()
        rv_upcoming.show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_upcoming, container, false)
    }

}
