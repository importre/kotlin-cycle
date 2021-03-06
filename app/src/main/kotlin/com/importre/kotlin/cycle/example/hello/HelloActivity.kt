package com.importre.kotlin.cycle.example.hello

import android.os.Bundle
import com.importre.kotlin.cycle.*
import com.importre.kotlin.cycle.example.BaseActivity
import com.importre.kotlin.cycle.example.R
import kotlinx.android.synthetic.main.activity_hello.*

class HelloActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hello)

        Cycle.run(main, DomSource())
    }

    private val main = { sources: Sources ->
        val change = sources.dom().select(helloEdit).textChanges()
        val model = change.map(::greeting)
        val view = model.map { message -> { helloText.text = message } }
        Sinks(DomSink(view))
    }
}
