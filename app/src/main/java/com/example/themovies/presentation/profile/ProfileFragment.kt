package com.example.themovies.presentation.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.themovies.R
import com.example.themovies.data.source.local.SharedPreference
import com.example.themovies.domain.entities.*
import com.example.themovies.presentation.profile.favorite.FavoriteFragment
import com.example.themovies.presentation.profile.watchlist.WatchlistFragment
import com.example.themovies.utils.*
import com.example.themovies.utils.vo.Status
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject

/**
 * request token -> validate with login (validateWithLogin) -> create session (createSession)
 */
class ProfileFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private lateinit var viewModel: ProfileViewModel

    private lateinit var preference: SharedPreference

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        preference = SharedPreference(requireContext())

        viewModel = ViewModelProvider(this, factory)[ProfileViewModel::class.java]

        initView()

        checkSession()

    }

    private fun initView() {
        // setup viewpager
        setupViewPager()
    }

    private fun checkSession() {
        val sessionId = preference.getString(KEY_SESSION)
        if (sessionId != null) {
            viewModel.checkSession(sessionId)

            viewModel.isLoggedIn.observe(viewLifecycleOwner, Observer {
                when (it) {
                    true -> observeGetProfileDetail(sessionId)
                    false -> {
                        layout_profile.hide()
                        tv_havent_login.show()
                    }
                }
            })
        } else {
            tv_havent_login.show()
        }
    }

    private fun setupViewPager() {
        val profileViewPagerAdapter = ProfileViewPagerAdapter(childFragmentManager)
        profileViewPagerAdapter.populateFragment(FavoriteFragment(), "Favorite")
        profileViewPagerAdapter.populateFragment(WatchlistFragment(), "Watchlist")

        view_pager.adapter = profileViewPagerAdapter
        tabs.setupWithViewPager(view_pager)
    }

    private fun observeGetProfileDetail(sessionId: String?) {
        viewModel.getProfileDetail(sessionId!!).observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> showLoading()
                Status.SUCCESS -> {
                    hideLoading()
                    showProfile(it.data)
                }
                Status.ERROR -> hideLoading()
            }
        })
    }
    private fun showProfile(profile: Profile?) {
        tv_first_char.text = "${firstCharOfUsername(profile!!.username)}"
        tv_username.text = profile.username
    }

    private fun showLoading() {
        progress_bar.hide()
    }

    private fun hideLoading() {
        progress_bar.hide()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

}
