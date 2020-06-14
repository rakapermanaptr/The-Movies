package com.example.themovies.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.themovies.data.TheMoviesRepository
import com.example.themovies.domain.entities.*
import com.example.themovies.utils.vo.Resource
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val repository: TheMoviesRepository) :
    ViewModel() {

    fun getRequestToken(): LiveData<Resource<RequestToken>> =
        repository.getRequestToken()

    fun validateTokenWithLogin(validateWithLogin: ValidateWithLogin) =
        repository.validateTokenWithLogin(validateWithLogin)

    fun createSession(createSession: CreateSession): LiveData<Resource<Session>> =
        repository.createSession(createSession)

    fun getUserProfile(sessionId: String): LiveData<Resource<Profile>> =
        repository.getProfileDetail(sessionId)
}

