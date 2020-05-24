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

    fun getPopularTvShows(): LiveData<Resource<List<TvShow>>> {
        val resultPopularTvShows = MutableLiveData<Resource<List<TvShow>>>()
        coroutineScope.launch {
            resultPopularTvShows.value = Resource.loading(null)
            try {
                val popularTvShows = service.getPopularTvShowsAsync("popularity.desc").await()
                resultPopularTvShows.value = Resource.success(popularTvShows.data)
            } catch (e: Exception) {
                resultPopularTvShows.value = Resource.error(e.localizedMessage, null)
                Log.e("RemoteDataSource", "getPopularTvShows Error : ${e.localizedMessage}")
            }
        }

        return resultPopularTvShows
    }

    fun getPopularDramaTvShows(): LiveData<Resource<List<TvShow>>> {
        val resultPopularDrama = MutableLiveData<Resource<List<TvShow>>>()
        coroutineScope.launch {
            resultPopularDrama.value = Resource.loading(null)
            try {
                val popularDrama = service.getPopularDramaTvShowsAsync(18, 2020).await()
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

    fun getTvShowDetail(tvShowId: Int): LiveData<Resource<TvShowDetail>> {
        val resultTvShowDetail = MutableLiveData<Resource<TvShowDetail>>()
        coroutineScope.launch {
            resultTvShowDetail.value = Resource.loading(null)
            try {
                val tvShowDetail = service.getTvShowDetailAsync(tvShowId).await()
                resultTvShowDetail.value = Resource.success(tvShowDetail)
            } catch (e: Exception) {
                resultTvShowDetail.value = Resource.error(e.localizedMessage, null)
                Log.e("RemoteDataSource", "getTvShowDetail Error : ${e.localizedMessage}")
            }
        }

        return resultTvShowDetail
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

    fun getTvShowCaster(tvShowId: Int): LiveData<Resource<List<Cast>>> {
        val resultTvShowCaster = MutableLiveData<Resource<List<Cast>>>()
        coroutineScope.launch {
            resultTvShowCaster.value = Resource.loading(null)
            try {
                val tvShowCaster = service.getTvShowCasterAsync(tvShowId).await()
                resultTvShowCaster.value = Resource.success(tvShowCaster.cast)
            } catch (e: Exception) {
                resultTvShowCaster.value = Resource.error(e.localizedMessage, null)
                Log.e("RemoteDataSource", "getTvShowCaster Error : ${e.localizedMessage}")
            }
        }

        return resultTvShowCaster
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

    fun getSimilarTvShows(tvShowId: Int): LiveData<Resource<List<TvShow>>> {
        val resultSimilarTvShows = MutableLiveData<Resource<List<TvShow>>>()
        coroutineScope.launch {
            resultSimilarTvShows.value = Resource.loading(null)
            try {
                val similarTvShows = service.getSimilarTvShowsAsync(tvShowId).await()
                resultSimilarTvShows.value = Resource.success(similarTvShows.data)
            } catch (e: Exception) {
                resultSimilarTvShows.value = Resource.error(e.localizedMessage, null)
                Log.e("RemoteDataSource", "getSimilarTvShows Error : ${e.localizedMessage}")
            }
        }

        return resultSimilarTvShows
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

}