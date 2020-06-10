package com.example.themovies.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.themovies.data.TheMoviesRepository
import com.example.themovies.domain.entities.*
import com.example.themovies.utils.vo.Resource
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val repository: TheMoviesRepository) :
    ViewModel() {

    private var tvShowId = MutableLiveData<Int>()
    private var movieId = MutableLiveData<Int>()

    private var _isSessionActive = MutableLiveData<Boolean>()
    val isSessionActive: LiveData<Boolean>
        get() = _isSessionActive

    fun checkSession(sessionId: String) {
        _isSessionActive.value = sessionId != ""
    }

    fun setSelectedTvShow(tvShowId: Int) {
        this.tvShowId.value = tvShowId
    }

    fun setSelectedMovie(movieId: Int) {
        this.movieId.value = movieId
    }

    var tvShowDetail: LiveData<Resource<TvShowDetail>> =
        Transformations.switchMap(tvShowId) { mTvShowId ->
            repository.getTvShowDetail(mTvShowId)
        }

    var movieDetail: LiveData<Resource<MovieDetail>> =
        Transformations.switchMap(movieId) { mMovieId ->
            repository.getMovieDetail(mMovieId)
        }

    var tvShowCaster: LiveData<Resource<List<Cast>>> =
        Transformations.switchMap(tvShowId) { mTvShowId ->
            repository.getTvShowCaster(mTvShowId)
        }

    var movieCaster: LiveData<Resource<List<Cast>>> =
        Transformations.switchMap(movieId) { mMovieId ->
            repository.getMovieCaster(mMovieId)
        }

    var similarTvShows: LiveData<Resource<List<TvShow>>> =
        Transformations.switchMap(tvShowId) { mTvShowId ->
            repository.getSimilarTvShows(mTvShowId)
        }

    var similarMovies: LiveData<Resource<List<Movie>>> =
        Transformations.switchMap(movieId) { mMovieId ->
            repository.getSimilarMovies(mMovieId)
        }
}