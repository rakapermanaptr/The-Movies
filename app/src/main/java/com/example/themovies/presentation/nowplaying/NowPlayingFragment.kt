package com.example.themovies.presentation.nowplaying

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
import com.example.themovies.utils.hide
import com.example.themovies.utils.show
import com.example.themovies.utils.vo.Status
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_now_playing.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class NowPlayingFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private lateinit var viewModel: NowPlayingViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this, factory)[NowPlayingViewModel::class.java]
        observeViewModel()

    }

    private fun observeViewModel() {
        viewModel.getNowPlaying().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> showLoading()
                Status.SUCCESS -> {
                    hideLoading()
                    showNowPlaying(it.data)
                }
                Status.ERROR -> hideLoading()
            }
        })
    }

    private fun showNowPlaying(nowPlayingList: List<Movie>?) {
        val nowPlayingAdapter = NowPlayingAdapter()
        nowPlayingAdapter.addItems(nowPlayingList!!)

        rv_nowPlaying.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = nowPlayingAdapter
        }
    }

    private fun showLoading() {
        progress_bar.show()
        rv_nowPlaying.hide()
    }

    private fun hideLoading() {
        progress_bar.hide()
        rv_nowPlaying.show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_now_playing, container, false)
    }

}
