package com.importre.kotlin.cycle.example.ext

import android.app.Activity
import android.support.annotation.StringRes
import android.widget.Toast

fun Activity.toast(@StringRes id: Int) = Toast.makeText(this, id, Toast.LENGTH_SHORT).show()
