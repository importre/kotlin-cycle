package com.importre.kotlin.cycle.example.rest.ext

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.loadUrl(imageUrl: String) = Picasso.with(context).load(imageUrl).into(this)

