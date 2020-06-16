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

    private var movieId = MutableLiveData<Int>()

    private var _isSessionActive = MutableLiveData<Boolean>()
    val isSessionActive: LiveData<Boolean>
        get() = _isSessionActive

    fun checkSession(sessionId: String) {
        _isSessionActive.value = sessionId != ""
    }

    fun setSelectedMovie(movieId: Int) {
        this.movieId.value = movieId
    }

    var movieDetail: LiveData<Resource<MovieDetail>> =
        Transformations.switchMap(movieId) { mMovieId ->
            repository.getMovieDetail(mMovieId)
        }

    var movieCaster: LiveData<Resource<List<Cast>>> =
        Transformations.switchMap(movieId) { mMovieId ->
            repository.getMovieCaster(mMovieId)
        }

    var similarMovies: LiveData<Resource<List<Movie>>> =
        Transformations.switchMap(movieId) { mMovieId ->
            repository.getSimilarMovies(mMovieId)
        }

    fun postFavoriteMovie(
        sessionId: String,
        favorite: Favorite
    ): LiveData<Resource<FavoriteResponse>> = repository.postFavoriteMovie(sessionId, favorite)

    fun postWatchlistMovie(
        sessionId: String,
        watchlist: Watchlist
    ): LiveData<Resource<WatchlistResponse>> = repository.postWatchlistMovie(sessionId, watchlist)

    fun getMovieStates(movieId: Int, sessionId: String): LiveData<Resource<MovieStates>> =
        repository.getMovieState(movieId, sessionId)

}