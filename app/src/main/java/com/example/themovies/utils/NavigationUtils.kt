package com.example.themovies.utils

import android.content.Context
import android.content.Intent
import com.example.themovies.presentation.detail.DetailActivity

object NavigationUtils {

    fun navigateToDetailActivity(context: Context,id: Int, type: String) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(KEY_ID, id)
        intent.putExtra(KEY_TYPE, type)
        context.startActivity(intent)
    }
}