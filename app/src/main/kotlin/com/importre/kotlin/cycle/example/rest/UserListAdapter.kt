package com.importre.kotlin.cycle.example.rest

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.importre.kotlin.cycle.example.R
import com.importre.kotlin.cycle.example.rest.model.User

class UserListAdapter(private val context: Context) : RecyclerView.Adapter<UserViewHolder>() {

    val users = arrayListOf<User>()

    override fun getItemCount() = users.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.layout_user_item, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder?, position: Int) {
        val user = users[position]
        holder?.setUser(user)
    }
}

