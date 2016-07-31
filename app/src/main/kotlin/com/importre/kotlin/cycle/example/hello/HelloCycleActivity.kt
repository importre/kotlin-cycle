package com.importre.kotlin.cycle.example.hello

import android.os.Bundle
import com.importre.kotlin.cycle.cycle
import com.importre.kotlin.cycle.example.BaseActivity
import com.importre.kotlin.cycle.example.R
import kotlinx.android.synthetic.main.activity_hello.*

class HelloCycleActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hello)

        cycle {
            val change = dom.select(helloEdit).textChanges()
            val model = change.map(::greeting)
            model.map { message -> { helloText.text = message } }
        }
    }
}
