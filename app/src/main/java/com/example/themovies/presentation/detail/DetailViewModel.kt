package com.example.themovies.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.themovies.data.TheMoviesRepository
import com.example.themovies.domain.entities.Cast
import com.example.themovies.domain.entities.TvShow
import com.example.themovies.domain.entities.TvShowDetail
import com.example.themovies.utils.vo.Resource
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val repository: TheMoviesRepository) :
    ViewModel() {

    private var tvShowId = MutableLiveData<Int>()

    fun setSelectedTvShow(tvShowId: Int) {
        this.tvShowId.value = tvShowId
    }

    var tvShowDetail: LiveData<Resource<TvShowDetail>> =
        Transformations.switchMap(tvShowId) { mTvShowId ->
            repository.getTvShowDetail(mTvShowId)
        }

    var tvShowCaster: LiveData<Resource<List<Cast>>> =
        Transformations.switchMap(tvShowId) { mTvShowId ->
            repository.getTvShowCaster(mTvShowId)
        }

    var similarTvShows: LiveData<Resource<List<TvShow>>> =
        Transformations.switchMap(tvShowId) { mTvShowId ->
            repository.getSimilarTvShows(mTvShowId)
        }

}