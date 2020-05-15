package com.example.themovies.utils

import android.view.View
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.INVISIBLE
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