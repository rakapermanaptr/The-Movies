package com.example.themovies.data.source.local

import android.content.Context
import android.content.SharedPreferences

class SharedPreference(context: Context) {

    private var PRIVATE_MODE = 0
    private val PREF_NAME = "saung-belajar-jasmine"

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)

    fun saveString(key: String, value: String) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    fun saveBoolean(key: String, value: Boolean) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }
}