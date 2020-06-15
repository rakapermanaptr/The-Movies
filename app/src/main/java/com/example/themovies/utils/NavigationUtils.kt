package com.example.themovies.utils

import android.content.Context
import android.content.Intent
import com.example.themovies.presentation.detail.DetailActivity

object NavigationUtils {

    fun navigateToDetailActivity(context: Context,id: Int) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(KEY_ID, id)
        context.startActivity(intent)
    }
}