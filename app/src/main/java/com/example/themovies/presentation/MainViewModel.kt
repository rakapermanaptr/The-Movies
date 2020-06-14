package com.example.themovies.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themovies.data.TheMoviesRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(repository: TheMoviesRepository) : ViewModel() {

    private val _isLoggedIn = MutableLiveData<Boolean>()
    val isLoggedIn: LiveData<Boolean>
        get() = _isLoggedIn

    fun checkSession(sessionId: String) {
        _isLoggedIn.value = sessionId != ""
    }
}