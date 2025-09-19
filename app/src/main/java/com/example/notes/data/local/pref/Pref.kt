package com.example.notes.data.local.pref

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.notes.utils.AppKey

class Pref(context: Context) {

    private val pref: SharedPreferences =
        context.getSharedPreferences(AppKey.KEY_PREF, Context.MODE_PRIVATE)

    fun saveUserSeen(value: Boolean) {
        pref.edit {
            putBoolean(AppKey.KEY_ON_BOARD, value)
        }
    }

    fun getUserSeen(): Boolean {
        return pref.getBoolean(AppKey.KEY_ON_BOARD, false)
    }
}