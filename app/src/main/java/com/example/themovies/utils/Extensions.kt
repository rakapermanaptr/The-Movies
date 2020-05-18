package com.example.themovies.utils

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun Fragment.showToast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun AppCompatActivity.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun releaseDate(input: String): String {
    val from = SimpleDateFormat("yyyy-MM-dd")
    val to = SimpleDateFormat("dd MMM yyyy")
    var date: Date? = null
    return try {
        date = from.parse(input)
        to.format(date)
    } catch (e: ParseException) {
        e.printStackTrace()
        input
    }
}