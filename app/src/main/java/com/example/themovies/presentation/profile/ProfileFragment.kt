package com.example.themovies.presentation.profile

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.themovies.R
import com.example.themovies.data.source.local.SharedPreference
import com.example.themovies.domain.entities.CreateSession
import com.example.themovies.domain.entities.RequestToken
import com.example.themovies.domain.entities.Session
import com.example.themovies.domain.entities.ValidateWithLogin
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

    }

    private fun initView() {
        parent_layout.setOnClickListener { requireActivity().hideKeyboard() }

        // button login action
        btn_login.setOnClickListener {

            if (edt_username.text.toString().isNotEmpty() && edt_password.text.toString().isNotEmpty()) {

                // 1. get request token
                viewModel.getRequestToken().observe(viewLifecycleOwner, Observer {
                    when (it.status) {
                        Status.LOADING -> showLoading()
                        Status.SUCCESS -> {
                            hideLoading()
                            showRequestToken(it.data)
                        }
                        Status.ERROR -> hideLoading()
                    }
                })

            }

            requireActivity().hideKeyboard()
        }

        // check session
        preference.getString(KEY_SESSION)?.let { viewModel.checkSession(it) }
        viewModel.isLoggedIn.observe(viewLifecycleOwner, Observer {
            when (it) {
                true -> layout_login.hide()
                false -> layout_login.show()
            }
        })

    }

    private fun showRequestToken(requestToken: RequestToken?) {
        val validateWithLogin = ValidateWithLogin(
            username = edt_username.text.toString(),
            password = edt_password.text.toString(),
            requestToken = requestToken!!.requestToken
        )
        // 2. validate token with login data
        viewModel.validateTokenWithLogin(validateWithLogin).observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> showLoading()
                Status.SUCCESS -> {
                    hideLoading()
                    validateWithLogin(it.data)
                }
                Status.ERROR -> hideLoading()
            }
        })
    }

    private fun validateWithLogin(requestToken: RequestToken?) {
        val createSession = CreateSession(requestToken = requestToken!!.requestToken)
        // 3. create session
        viewModel.createSession(createSession).observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> showLoading()
                Status.SUCCESS -> {
                    hideLoading()
                    createSession(it.data)
                }
                Status.ERROR -> hideLoading()
            }
        })
    }

    private fun createSession(session: Session?) {
        // save session to the sharedPref
        preference.saveString(KEY_SESSION, session!!.sessionId)

        if (session.success) {
            layout_login.hide()
            showToast("Welcome...")
        }
    }

    private fun showLoading() {
        progress_bar.show()
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
