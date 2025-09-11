package com.example.notes.utils

import android.widget.ImageView
import coil.load

fun ImageView.loadImg(url: String) {
    this.load(url) {
        crossfade(true)
    }
}