package com.example.themovies.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.themovies.data.source.remote.network.TheMoviesService
import com.example.themovies.domain.entities.*
import com.example.themovies.utils.vo.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RemoteDataSource(private val service: TheMoviesService) {

    private val job = Job()
    private val coroutineScope = CoroutineScope(job + Dispatchers.Main)

    fun getPopularMovies(): LiveData<Resource<List<Movie>>> {
        val resultPopularMovies = MutableLiveData<Resource<List<Movie>>>()
        coroutineScope.launch {
            resultPopularMovies.value = Resource.loading(null)
            try {
                val popularMovies = service.getPopularMoviesAsync("popularity.desc").await()
                resultPopularMovies.value = Resource.success(popularMovies.data)
            } catch (e: Exception) {
                resultPopularMovies.value = Resource.error(e.localizedMessage, null)
                Log.e("RemoteDataSource", "getPopularMovies Error : ${e.localizedMessage}")
            }
        }

        return resultPopularMovies
    }

    fun getMostPopularThriller(): LiveData<Resource<List<Movie>>> {
        val resultMostPopularThriller = MutableLiveData<Resource<List<Movie>>>()
        coroutineScope.launch {
            resultMostPopularThriller.value = Resource.loading(null)
            try {
                val bestActionMovies = service.getMostPopularThrillerAsync(53, 2020).await()
                resultMostPopularThriller.value = Resource.success(bestActionMovies.data)
            } catch (e: Exception) {
                resultMostPopularThriller.value = Resource.error(e.localizedMessage, null)
                Log.e("RemoteDataSource", "getPopularTvShows Error : ${e.localizedMessage}")
            }
        }

        return resultMostPopularThriller
    }

    fun getPopularDrama(): LiveData<Resource<List<Movie>>> {
        val resultPopularDrama = MutableLiveData<Resource<List<Movie>>>()
        coroutineScope.launch {
            resultPopularDrama.value = Resource.loading(null)
            try {
                val popularDrama = service.getPopularDramaAsync(18, 2020).await()
                resultPopularDrama.value = Resource.success(popularDrama.data)
            } catch (e: Exception) {
                resultPopularDrama.value = Resource.error(e.localizedMessage, null)
                Log.e("RemoteDataSource", "getPopularDramaTvShows Error : ${e.localizedMessage}")
            }
        }

        return resultPopularDrama
    }

    fun getPopularActionMovies(): LiveData<Resource<List<Movie>>> {
        val resultPopularAction = MutableLiveData<Resource<List<Movie>>>()
        coroutineScope.launch {
            resultPopularAction.value = Resource.loading(null)
            try {
                val popularAction = service.getPopularActionMoviesAsync(28, 2020).await()
                resultPopularAction.value = Resource.success(popularAction.data)
            } catch (e: Exception) {
                resultPopularAction.value = Resource.error(e.localizedMessage, null)
                Log.e("RemoteDataSource", "getPopularActionMovies Error : ${e.localizedMessage}")
            }
        }

        return resultPopularAction
    }

    fun getNowPlayingMovies(): LiveData<Resource<List<Movie>>> {
        val resultNowPlayingMovies = MutableLiveData<Resource<List<Movie>>>()
        coroutineScope.launch {
            resultNowPlayingMovies.value = Resource.loading(null)
            try {
                val nowPlayingMovies = service.getNowPlayingMoviesAsync().await()
                resultNowPlayingMovies.value = Resource.success(nowPlayingMovies.data)
            } catch (e: Exception) {
                resultNowPlayingMovies.value = Resource.error(e.localizedMessage, null)
                Log.e("RemoteDataSource", "getNowPlayingMovies Error : ${e.localizedMessage}")
            }
        }

        return resultNowPlayingMovies
    }

    fun getUpcomingMovies(): LiveData<Resource<List<Movie>>> {
        val resultUpcomingMovies = MutableLiveData<Resource<List<Movie>>>()
        coroutineScope.launch {
            resultUpcomingMovies.value = Resource.loading(null)
            try {
                val upcomingMovies = service.getUpcomingMoviesAsync().await()
                resultUpcomingMovies.value = Resource.success(upcomingMovies.data)
            } catch (e: Exception) {
                resultUpcomingMovies.value = Resource.error(e.localizedMessage, null)
                Log.e("RemoteDataSource", "getUpcomingMovies Error : ${e.localizedMessage}")
            }
        }

        return resultUpcomingMovies
    }

    fun getMovieDetail(movieId: Int): LiveData<Resource<MovieDetail>> {
        val resultMovieDetail = MutableLiveData<Resource<MovieDetail>>()
        coroutineScope.launch {
            resultMovieDetail.value = Resource.loading(null)
            try {
                val movieDetail = service.getMovieDetailAsync(movieId).await()
                resultMovieDetail.value = Resource.success(movieDetail)
            } catch (e: Exception) {
                resultMovieDetail.value = Resource.error(e.localizedMessage, null)
                Log.e("RemoteDataSource", "getMovieDetail Error : ${e.localizedMessage}")
            }
        }

        return resultMovieDetail
    }

    fun getMovieCaster(movieId: Int): LiveData<Resource<List<Cast>>> {
        val resultMovieCaster = MutableLiveData<Resource<List<Cast>>>()
        coroutineScope.launch {
            resultMovieCaster.value = Resource.loading(null)
            try {
                val movieCaster = service.getMovieCasterAsync(movieId).await()
                resultMovieCaster.value = Resource.success(movieCaster.cast)
            } catch (e: Exception) {
                resultMovieCaster.value = Resource.error(e.localizedMessage, null)
                Log.e("RemoteDataSource", "getMovieCaster Error : ${e.localizedMessage}")
            }
        }

        return resultMovieCaster
    }

    fun getSimilarMovies(movieId: Int): LiveData<Resource<List<Movie>>> {
        val resultSimilarMovies = MutableLiveData<Resource<List<Movie>>>()
        coroutineScope.launch {
            resultSimilarMovies.value = Resource.loading(null)
            try {
                val similarMovies = service.getSimilarMoviesAsync(movieId).await()
                resultSimilarMovies.value = Resource.success(similarMovies.data)
            } catch (e: Exception) {
                resultSimilarMovies.value = Resource.error(e.localizedMessage, null)
                Log.e("RemoteDataSource", "getSimilarMovies Error : ${e.localizedMessage}")
            }
        }

        return resultSimilarMovies
    }

    fun getRequestToken(): LiveData<Resource<RequestToken>> {
        val resultRequestToken = MutableLiveData<Resource<RequestToken>>()
        coroutineScope.launch {
            resultRequestToken.value = Resource.loading(null)
            try {
                val requestToken = service.getRequestTokenAsync().await()
                resultRequestToken.value = Resource.success(requestToken)
            } catch (e: Exception) {
                resultRequestToken.value = Resource.error(e.localizedMessage, null)
                Log.e("RemoteDataSource", "getRequestToken Error : ${e.localizedMessage}")
            }
        }

        return resultRequestToken
    }

    fun validateTokenWithLogin(dataLogin: ValidateWithLogin): LiveData<Resource<RequestToken>> {
        val resultValidateWithLogin = MutableLiveData<Resource<RequestToken>>()
        coroutineScope.launch {
            resultValidateWithLogin.value = Resource.loading(null)
            try {
                val validateWithLogin = service.validateTokenWithLoginAsync(dataLogin).await()
                resultValidateWithLogin.value = Resource.success(validateWithLogin)
            } catch (e: Exception) {
                resultValidateWithLogin.value = Resource.error(e.localizedMessage, null)
                Log.e("RemoteDataSource", "validateWithLogin Error : ${e.localizedMessage}")
            }
        }

        return resultValidateWithLogin
    }

    fun createSession(createSession: CreateSession): LiveData<Resource<Session>> {
        val resultCreateSession = MutableLiveData<Resource<Session>>()
        coroutineScope.launch {
            resultCreateSession.value = Resource.loading(null)
            try {
                val session = service.createSessionAsync(createSession).await()
                resultCreateSession.value = Resource.success(session)
            } catch (e: Exception) {
                resultCreateSession.value = Resource.error(e.localizedMessage, null)
                Log.e("RemoteDataSource", "createSession Error : ${e.localizedMessage}")
            }
        }

        return resultCreateSession
    }

    fun getProfileDetail(sessionId: String): LiveData<Resource<Profile>> {
        val resultAccountDetail = MutableLiveData<Resource<Profile>>()
        coroutineScope.launch {
            resultAccountDetail.value = Resource.loading(null)
            try {
                val account = service.getProfileDetailAsync(sessionId).await()
                resultAccountDetail.value = Resource.success(account)
            } catch (e: Exception) {
                resultAccountDetail.value = Resource.error(e.localizedMessage, null)
                Log.e("RemoteDataSource", "getAccountDetail Error : ${e.localizedMessage}")
            }
        }

        return resultAccountDetail
    }

    fun postFavoriteMovie(sessionId: String, favorite: Favorite): LiveData<Resource<FavoriteResponse>> {
        val resultFavorite = MutableLiveData<Resource<FavoriteResponse>>()
        coroutineScope.launch {
            resultFavorite.value = Resource.loading(null)
            try {
                val favoriteResponse = service.postFavoriteAsync(sessionId, favorite).await()
                resultFavorite.value = Resource.success(favoriteResponse)
            } catch (e: Exception) {
                resultFavorite.value = Resource.error(e.localizedMessage, null)
                Log.e("RemoteDataSource", "postFavoriteMovie Error : ${e.localizedMessage}")
            }
        }

        return resultFavorite
    }

    fun postWatchlistMovie(sessionId: String, watchlist: Watchlist): LiveData<Resource<WatchlistResponse>> {
        val resultWatchlist = MutableLiveData<Resource<WatchlistResponse>>()
        coroutineScope.launch {
            resultWatchlist.value = Resource.loading(null)
            try {
                val watchlistResponse = service.postWatchlistAsync(sessionId, watchlist).await()
                resultWatchlist.value = Resource.success(watchlistResponse)
            } catch (e: Exception) {
                resultWatchlist.value = Resource.error(e.localizedMessage, null)
                Log.e("RemoteDataSource", "postWatchlistMovie Error : ${e.localizedMessage}")
            }
        }

        return resultWatchlist
    }

    fun getFavoriteMovies(sessionId: String): LiveData<Resource<List<Movie>>> {
        val resultFavoriteMovies = MutableLiveData<Resource<List<Movie>>>()
        coroutineScope.launch {
            resultFavoriteMovies.value = Resource.loading(null)
            try {
                val favoriteMovies = service.getFavoriteMoviesAsync(sessionId).await()
                resultFavoriteMovies.value = Resource.success(favoriteMovies.data)
            } catch (e: Exception) {
                resultFavoriteMovies.value = Resource.error(e.localizedMessage, null)
                Log.e("RemoteDataSource", "getFavoriteMovies Error : ${e.localizedMessage}")
            }
        }

        return resultFavoriteMovies
    }

    fun getWatchlistMovies(sessionId: String): LiveData<Resource<List<Movie>>> {
        val resultWatchlistMovies = MutableLiveData<Resource<List<Movie>>>()
        coroutineScope.launch {
            resultWatchlistMovies.value = Resource.loading(null)
            try {
                val watchlistMovies = service.getWatchlistMoviesAsync(sessionId).await()
                resultWatchlistMovies.value = Resource.success(watchlistMovies.data)
            } catch (e: Exception) {
                resultWatchlistMovies.value = Resource.error(e.localizedMessage, null)
                Log.e("RemoteDataSource", "getWatchlistMovies Error : ${e.localizedMessage}")
            }
        }

        return resultWatchlistMovies
    }

    fun getMovieStates(movieId: Int, sessionId: String): LiveData<Resource<MovieStates>> {
        val resultMovieStates = MutableLiveData<Resource<MovieStates>>()
        coroutineScope.launch {
            resultMovieStates.value = Resource.loading(null)
            try {
                val movieStates = service.getMovieStatesAsync(movieId, sessionId).await()
                resultMovieStates.value = Resource.success(movieStates)
            } catch (e: Exception) {
                resultMovieStates.value = Resource.error(e.localizedMessage, null)
                Log.e("RemoteDataSource", "getMovieStates Error : ${e.localizedMessage}")
            }
        }

        return resultMovieStates
    }

}