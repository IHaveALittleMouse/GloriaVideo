package com.gloria.common.card

import android.content.Context
import android.util.Log
import android.widget.ImageView
import com.squareup.picasso.Picasso

object Utils {
    fun loadImageFromUrl(context: Context, url: String, imageView: ImageView) {
        val picasso = Picasso.Builder(context).listener { picasso, uri, exception ->
            Log.d("Picasso", exception.toString())
        }.build()
        picasso.isLoggingEnabled = true
        picasso.load(url).fit().into(imageView)
    }
}