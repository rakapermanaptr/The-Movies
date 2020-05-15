package com.example.themovies.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.themovies.R
import com.example.themovies.presentation.discover.DiscoverFragment
import com.example.themovies.presentation.nowplaying.NowPlayingFragment
import com.example.themovies.presentation.profile.ProfileFragment
import com.example.themovies.presentation.upcoming.UpcomingFragment
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_toolbar.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            var fragment: Fragment?
            when (item.itemId) {
                R.id.nav_discover -> {
                    fragment = DiscoverFragment()
                    setToolbarTitle("Discover")
                    loadFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_nowplaying -> {
                    fragment = NowPlayingFragment()
                    setToolbarTitle("Now Playing")
                    loadFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_upcoming -> {
                    fragment = UpcomingFragment()
                    setToolbarTitle("Upcoming")
                    loadFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_profile -> {
                    fragment = ProfileFragment()
                    setToolbarTitle("Profile")
                    loadFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            loadFragment(DiscoverFragment())
            setToolbarTitle("Discover")
        }

        bttm_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, fragment)
            .commit()
    }

    private fun setToolbarTitle(title: String) {
        tv_title_toolbar.text = title
    }
}
