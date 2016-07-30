package com.importre.kotlin.cycle.example.rest

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.importre.kotlin.cycle.*
import com.importre.kotlin.cycle.example.BaseActivity
import com.importre.kotlin.cycle.example.R
import com.importre.kotlin.cycle.example.app.CycleApp
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

        Cycle.run(main, DomSource(DomDriver(root)))
    }

    private fun inject() {
        (application as CycleApp).comp.inject(this)
    }

    private fun initView() {
        val layoutManager = LinearLayoutManager(this)
        userListView.layoutManager = layoutManager
        userListView.adapter = adapter
    }

    private val main = { sources: Sources ->
        // Intent
        val usersStream = api.getUsers()
        val refreshStream = sources.dom()
                .select(refreshView)
                .refreshes()
                .startWith(null as Void?)
        val changeStream = Observable
                .combineLatest(usersStream, refreshStream) { users, refresh -> users }

        // Model
        val modelStream = changeStream

        // View
        val viewStream = modelStream.map { users -> onUpdateView(users) }

        Sinks(DomSink(viewStream, onError))
    }

    private val onError = { error: Throwable ->
        Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
    }

    private fun onUpdateView(users: List<User>) = {
        adapter.users.clear()
        adapter.users.addAll(users)
        adapter.notifyDataSetChanged()
        refreshView.isRefreshing = false
    }
}
