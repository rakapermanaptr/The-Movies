package com.example.themovies.presentation.profile.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.themovies.data.TheMoviesRepository
import com.example.themovies.domain.entities.Movie
import com.example.themovies.utils.vo.Resource
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(private val repository: TheMoviesRepository) : ViewModel() {

    fun getFavoriteMovies(sessionId: String): LiveData<Resource<List<Movie>>> =
        repository.getFavoriteMovies(sessionId)
}