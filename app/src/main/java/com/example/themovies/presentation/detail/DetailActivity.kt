package com.example.themovies.presentation.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.themovies.R
import com.example.themovies.data.source.local.SharedPreference
import com.example.themovies.domain.entities.*
import com.example.themovies.utils.*
import com.example.themovies.utils.vo.Status
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private lateinit var viewModel: DetailViewModel

    private lateinit var preference: SharedPreference

    private var id: Int = 0
    private lateinit var type: String
    private var isFavorite: Boolean = false

    private val casterAdapter = CasterAdapter { cast ->
        showToast(cast.name)
    }

    private val similarTvShowsAdapter = SimilarTvShowsAdapter { tvShow ->
        NavigationUtils.navigateToDetailActivity(this, tvShow.id, TV_SHOW)
        finish()
    }

    private val similarMoviesAdapter = SimilarMoviesAdapter { movie ->
        NavigationUtils.navigateToDetailActivity(this, movie.id, MOVIE)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // get id and type
        id = intent.getIntExtra(KEY_ID, 0)
        type = intent.getStringExtra(KEY_TYPE)!!

        // shared preference
        preference = SharedPreference(this)

        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]
        observeViewModel()

        initView()

        checkSession()
    }

    // need to refactor this function
    private fun observeViewModel() {
        when (type) {
            TV_SHOW -> {
                viewModel.setSelectedTvShow(id)
                viewModel.tvShowDetail.observe(this, Observer {
                    when (it.status) {
                        Status.LOADING -> showLoading()
                        Status.SUCCESS -> {
                            hideLoading()
                            showTvShowDetail(it.data)
                        }
                        Status.ERROR -> hideLoading()
                    }
                })

                viewModel.tvShowCaster.observe(this, Observer {
                    when (it.status) {
                        Status.LOADING -> showLoading()
                        Status.SUCCESS -> {
                            hideLoading()
                            showTvShowCaster(it.data)
                        }
                        Status.ERROR -> hideLoading()
                    }
                })

                viewModel.similarTvShows.observe(this, Observer {
                    when (it.status) {
                        Status.LOADING -> showLoading()
                        Status.SUCCESS -> {
                            hideLoading()
                            showSimilarTvShows(it.data)
                        }
                        Status.ERROR -> hideLoading()
                    }
                })
            }
            MOVIE -> {
                viewModel.setSelectedMovie(id)
                viewModel.movieDetail.observe(this, Observer {
                    when (it.status) {
                        Status.LOADING -> showLoading()
                        Status.SUCCESS -> {
                            hideLoading()
                            showMovieDetail(it.data)
                        }
                        Status.ERROR -> hideLoading()
                    }
                })

                viewModel.movieCaster.observe(this, Observer {
                    when (it.status) {
                        Status.LOADING -> showLoading()
                        Status.SUCCESS -> {
                            hideLoading()
                            showMovieCaster(it.data)
                        }
                        Status.ERROR -> hideLoading()
                    }
                })

                viewModel.similarMovies.observe(this, Observer {
                    when (it.status) {
                        Status.LOADING -> showLoading()
                        Status.SUCCESS -> {
                            hideLoading()
                            showSimilarMovies(it.data)
                        }
                        Status.ERROR -> hideLoading()
                    }
                })
            }
        }
    }

    private fun initView() {
        var isLayoutMoreVisible = false

        // do some action and condition to "READ MORE" TextView
        tv_readMoreLess.setOnClickListener {
            if (!isLayoutMoreVisible) {
                isLayoutMoreVisible = true
                tv_readMoreLess.text = getString(R.string.read_less)
                layout_more_info.show()
            } else {
                isLayoutMoreVisible = false
                tv_readMoreLess.text = getString(R.string.read_more)
                layout_more_info.hide()
            }
        }

        // do some action and condition to overview
        tv_overview.setOnClickListener {
            if (tv_overview.maxLines == 5) tv_overview.maxLines = 99 else tv_overview.maxLines = 5
        }

        checkMovieStates(id, preference.getString(KEY_SESSION)!!)
    }

    private fun checkMovieStates(movieId: Int, sessionId: String) {
        viewModel.getMovieStates(movieId, sessionId).observe(this, Observer {
            when (it.status) {
                Status.LOADING -> showLoading()
                Status.SUCCESS -> {
                    hideLoading()

                    isFavorite = it.data!!.favorite

                    setMovieStates(isFavorite)
                }
                Status.ERROR -> hideLoading()
            }
        })
    }

    private fun setMovieStates(isFavorite: Boolean) {
        if (isFavorite) {
            fab_favorite.colorNormal = ContextCompat.getColor(this, R.color.colorPrimary)
            fab_favorite.colorPressed = ContextCompat.getColor(this, R.color.colorPrimary)
        } else {
            fab_favorite.colorNormal = ContextCompat.getColor(this, R.color.colorWhite)
            fab_favorite.colorPressed = ContextCompat.getColor(this, R.color.colorWhite)
        }
    }

    private fun checkSession() {
        // session checking
        val sessionId = preference.getString(KEY_SESSION)
        if (sessionId != null) {
            viewModel.checkSession(sessionId)

            viewModel.isSessionActive.observe(this, Observer {
                if (it) {
                    fab_favorite.setOnClickListener {

                        if (isFavorite) {
                            val favorite = Favorite(favorite = false, mediaId = id, mediaType = "movie")

                            addOrRemoveFavoriteMovie(sessionId, favorite)
                        } else {
                            val favorite = Favorite(favorite = true, mediaId = id, mediaType = "movie")

                            addOrRemoveFavoriteMovie(sessionId, favorite)
                        }

                    }

                    fab_watchlist.setOnClickListener { showToast("Added to your watchlist") }
                } else {
                    fab_favorite.setOnClickListener { showToast("You are not login") }

                    fab_watchlist.setOnClickListener { showToast("You are not login") }
                }
            })
        } else {
            fab_favorite.setOnClickListener { showToast("You are not login") }

            fab_watchlist.setOnClickListener { showToast("You are not login") }
        }
    }

    private fun addOrRemoveFavoriteMovie(sessionId: String, favorite: Favorite) {
        viewModel.postFavoriteMovie(sessionId, favorite).observe(this, Observer {
            when (it.status) {
                Status.LOADING -> showLoading()
                Status.SUCCESS -> {
                    showToast(it.data!!.statusMessage)
                    checkMovieStates(id, sessionId)
                }
                Status.ERROR -> hideLoading()
            }
        })
    }

    private fun showTvShowDetail(tvShowDetail: TvShowDetail?) {
        Glide.with(this)
            .load(BASE_IMAGE_URL + tvShowDetail?.backdropPath)
            .into(img_backdrop_detail)

        tv_title.text = tvShowDetail?.name
        tv_voteAverage.text = tvShowDetail?.voteAverage.toString()
        tv_overview.text = tvShowDetail?.overview
        tv_releaseDate.text = releaseDate(tvShowDetail!!.firstAirDate)
        tv_status.text = tvShowDetail.status

        tv_header_duration.text = getString(R.string.episode)
        tv_duration.text = "${tvShowDetail.numberOfEpisodes} episodes"

    }

    private fun showMovieDetail(movieDetail: MovieDetail?) {
        Glide.with(this)
            .load(BASE_IMAGE_URL + movieDetail?.backdropPath)
            .into(img_backdrop_detail)

        tv_title.text = movieDetail?.title
        tv_voteAverage.text = movieDetail?.voteAverage.toString()
        tv_overview.text = movieDetail?.overview
        tv_releaseDate.text = releaseDate(movieDetail!!.releaseDate)
        tv_status.text = movieDetail.status

        tv_header_duration.text = getString(R.string.duration)
        tv_duration.text = "${movieDetail.runtime} min"
    }

    private fun showTvShowCaster(casterList: List<Cast>?) {
        casterAdapter.addItems(casterList!!)

        rv_caster.apply {
            layoutManager = LinearLayoutManager(this@DetailActivity, RecyclerView.HORIZONTAL, false)
            adapter = casterAdapter
        }
    }

    private fun showMovieCaster(casterList: List<Cast>?) {
        casterAdapter.addItems(casterList!!)

        rv_caster.apply {
            layoutManager = LinearLayoutManager(this@DetailActivity, RecyclerView.HORIZONTAL, false)
            adapter = casterAdapter
        }
    }

    private fun showSimilarTvShows(tvShowList: List<TvShow>?) {
        similarTvShowsAdapter.addItems(tvShowList!!)

        rv_similarMovies.apply {
            layoutManager = LinearLayoutManager(this@DetailActivity, RecyclerView.HORIZONTAL, false)
            adapter = similarTvShowsAdapter
        }
    }

    private fun showSimilarMovies(movieList: List<Movie>?) {
        similarMoviesAdapter.addItems(movieList!!)

        rv_similarMovies.apply {
            layoutManager = LinearLayoutManager(this@DetailActivity, RecyclerView.HORIZONTAL, false)
            adapter = similarMoviesAdapter
        }
    }

    private fun showLoading() {
        progress_bar.show()
    }

    private fun hideLoading() {
        progress_bar.hide()
    }

}
