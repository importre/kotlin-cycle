package com.importre.kotlin.cycle.example.rest

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.importre.kotlin.cycle.cycle
import com.importre.kotlin.cycle.example.BaseActivity
import com.importre.kotlin.cycle.example.R
import com.importre.kotlin.cycle.example.app.CycleApp
import com.importre.kotlin.cycle.example.ext.toast
import com.importre.kotlin.cycle.example.rest.api.JsonPlaceholder
import com.importre.kotlin.cycle.example.rest.model.User
import kotlinx.android.synthetic.main.activity_users.*
import rx.Observable
import javax.inject.Inject

class UsersActivity : BaseActivity() {

    @Inject lateinit var api: JsonPlaceholder

    private val adapter by lazy { UserListAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        inject()
        initView()

        cycle {
            error = onError

            // Intent
            val usersStream = api.getUsers()
            val refreshStream = dom
                    .select(refreshView)
                    .refreshes()
                    .startWith(null as Void?)

            // Model
            val modelStream = Observable
                    .combineLatest(usersStream, refreshStream) { users, refresh ->
                        users
                    }

            // View
            modelStream.map { users -> onUpdateView(users) }
        }
    }

    private fun inject() {
        (application as CycleApp).comp.inject(this)
    }

    private fun initView() {
        val layoutManager = LinearLayoutManager(this)
        userListView.layoutManager = layoutManager
        userListView.adapter = adapter
    }

    private val onError = { error: Throwable ->
        error.message?.let { toast(it) }
    }

    private fun onUpdateView(users: List<User>) = {
        adapter.users.clear()
        adapter.users.addAll(users)
        adapter.notifyDataSetChanged()
        refreshView.isRefreshing = false
    }
}
