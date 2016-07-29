package com.importre.kotlin.cycle.example.ext

import android.app.Activity
import android.support.annotation.StringRes
import android.support.v7.widget.RecyclerView
import android.widget.Toast

fun Activity.toast(@StringRes id: Int)
        = Toast.makeText(this, id, Toast.LENGTH_SHORT).show()

fun RecyclerView.ViewHolder.toast(message: String)
        = Toast.makeText(itemView.context, message, Toast.LENGTH_SHORT).show()
