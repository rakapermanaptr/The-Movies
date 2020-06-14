package com.example.themovies.presentation.login

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.themovies.R
import com.example.themovies.data.source.local.SharedPreference
import com.example.themovies.domain.entities.CreateSession
import com.example.themovies.domain.entities.ValidateWithLogin
import com.example.themovies.utils.*
import com.example.themovies.utils.vo.Status
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private lateinit var viewModel: LoginViewModel

    private lateinit var preference: SharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        preference = SharedPreference(this)

        viewModel = ViewModelProvider(this, factory)[LoginViewModel::class.java]

        initView()

    }

    private fun initView() {
        setupToolbar()

        parent_layout.setOnClickListener { hideKeyboard() }

        btn_login.setOnClickListener {
            if (edt_username.text.toString() != "" || edt_password.text.toString() != "") {
                // 1. request token
                viewModel.getRequestToken().observe(this, Observer {
                    when (it.status) {
                        Status.LOADING -> showLoading()
                        Status.SUCCESS -> {
                            val validateWithLogin = ValidateWithLogin(
                                username = edt_username.text.toString(),
                                password = edt_password.text.toString(),
                                requestToken = it.data!!.requestToken
                            )
                            validateWithLogin(validateWithLogin)
                        }
                        Status.ERROR -> hideLoading()
                    }
                })
            } else {
                showToast("Username or Password cannot be empty")
            }
        }
    }

    private fun validateWithLogin(validateWithLogin: ValidateWithLogin?) {
        // 2. validate token with login
        viewModel.validateTokenWithLogin(validateWithLogin!!).observe(this, Observer {
            when (it.status) {
                Status.LOADING -> showLoading()
                Status.SUCCESS -> {
                    when (it.data!!.success) {
                        true -> {
                            val createSession = CreateSession(requestToken = it.data.requestToken)
                            createSession(createSession)
                        }
                        false -> showErrorMessage(it.message)
                    }
                }
                Status.ERROR -> {
                    hideLoading()
                    showErrorMessage("Invalid username and/or password")
                }
            }
        })
    }

    private fun createSession(createSession: CreateSession?) {
        // 3. create session
        viewModel.createSession(createSession!!).observe(this, Observer {
            when (it.status) {
                Status.LOADING -> showLoading()
                Status.SUCCESS -> {
                    when (it.data!!.success) {
                        true -> {
                            showProfile(it.data.sessionId)
                            saveSession(it.data.sessionId)
                        }
                        false -> {
                            showErrorMessage(it.message)
                        }
                    }
                }
                Status.ERROR -> hideLoading()
            }
        })
    }

    private fun showProfile(sessionId: String?) {
        viewModel.getUserProfile(sessionId!!).observe(this, Observer {
            when (it.status) {
                Status.LOADING -> showLoading()
                Status.SUCCESS -> {
                    showToast("Hello, ${it.data!!.username}")
                }
                Status.ERROR -> hideLoading()
            }
        })
    }

    private fun saveSession(sessionId: String?) {
        preference.saveString(KEY_SESSION, sessionId!!)
        finish()
    }

    private fun showLoading() {
        progress_bar.show()
    }

    private fun hideLoading() {
        progress_bar.hide()
    }

    private fun showErrorMessage(message: String?) {
        showToast(message!!)
    }

    private fun setupToolbar() {
        supportActionBar!!.setBackgroundDrawable(
            ColorDrawable(
                ContextCompat.getColor(
                    this,
                    R.color.colorWhite
                )
            )
        )
    }
}