package com.example.themovies.presentation.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themovies.data.TheMoviesRepository
import com.example.themovies.domain.entities.*
import com.example.themovies.utils.vo.Resource
import javax.inject.Inject

class ProfileViewModel @Inject constructor(private val repository: TheMoviesRepository) :
    ViewModel() {

    private val _isLoggedIn = MutableLiveData<Boolean>()
    val isLoggedIn: LiveData<Boolean>
        get() = _isLoggedIn

    fun checkSession(sessionId: String) {
        _isLoggedIn.value = sessionId != ""
    }

    fun getRequestToken(): LiveData<Resource<RequestToken>> =
        repository.getRequestToken()

    fun validateTokenWithLogin(validateWithLogin: ValidateWithLogin) =
        repository.validateTokenWithLogin(validateWithLogin)

    fun createSession(createSession: CreateSession): LiveData<Resource<Session>> =
        repository.createSession(createSession)

    fun getProfileDetail(sessionId: String): LiveData<Resource<Profile>> =
        repository.getProfileDetail(sessionId)
}