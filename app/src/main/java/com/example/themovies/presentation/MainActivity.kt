package com.example.themovies.presentation

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.themovies.R
import com.example.themovies.data.source.local.SharedPreference
import com.example.themovies.presentation.discover.DiscoverFragment
import com.example.themovies.presentation.login.LoginActivity
import com.example.themovies.presentation.nowplaying.NowPlayingFragment
import com.example.themovies.presentation.profile.ProfileFragment
import com.example.themovies.presentation.upcoming.UpcomingFragment
import com.example.themovies.utils.KEY_SESSION
import com.example.themovies.utils.showToast
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            val fragment: Fragment?
            when (item.itemId) {
                R.id.nav_discover -> {
                    fragment = DiscoverFragment()
                    loadFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_nowplaying -> {
                    fragment = NowPlayingFragment()
                    loadFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_upcoming -> {
                    fragment = UpcomingFragment()
                    loadFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_profile -> {
                    fragment = ProfileFragment()
                    loadFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private lateinit var viewModel: MainViewModel

    private lateinit var preference: SharedPreference

    private lateinit var menu: Menu

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.setBackgroundDrawable(
            ColorDrawable(
                ContextCompat.getColor(
                    this,
                    R.color.colorWhite
                )
            )
        )

        if (savedInstanceState == null) {
            loadFragment(DiscoverFragment())
        }

        preference = SharedPreference(this)

        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
        observeViewModel()

        bttm_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun observeViewModel() {
        val sessionId = preference.getString(KEY_SESSION)
        sessionId?.let { viewModel.checkSession(it) }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, fragment)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        viewModel.isLoggedIn.observe(this, Observer {
            when (it) {
                true -> menu.findItem(R.id.nav_login).title = "Logout"
                false -> menu.findItem(R.id.nav_login).title = "Login"
            }
        })
        this.menu = menu
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setActionMenu(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setActionMenu(selectedNav: Int) {
        when (selectedNav) {
            R.id.nav_login -> {
                viewModel.isLoggedIn.observe(this, Observer {
                    when (it) {
                        true -> {
                            showToast("is login")
                        }
                        false -> startLoginActivity()
                    }
                })
            }
        }
    }

    private fun startLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
    }
}
