package com.example.notes.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class Pref(context: Context) {

    private val pref: SharedPreferences =
        context.getSharedPreferences("onBoardPref", Context.MODE_PRIVATE)

    fun isOnBoardShown(): Boolean {
        return pref.getBoolean("isShown", false)
    }

    fun setOnBoardShown() {
        pref.edit().putBoolean("isShown", true).apply()
    }
}