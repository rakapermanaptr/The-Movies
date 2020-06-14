package com.example.themovies.presentation.login

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.themovies.R
import com.example.themovies.data.source.local.SharedPreference
import com.example.themovies.di.ViewModelFactory
import com.example.themovies.utils.hide
import com.example.themovies.utils.show
import com.example.themovies.utils.showToast
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

        viewModel = ViewModelProvider(this, factory)[LoginViewModel::class.java]
        preference = SharedPreference(this)

        initView()

    }

    private fun initView() {
        setupToolbar()
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