package com.importre.kotlin.cycle.example

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.importre.kotlin.cycle.example.bmi.BmiActivity
import com.importre.kotlin.cycle.example.check.CheckActivity
import com.importre.kotlin.cycle.example.hello.HelloActivity
import com.importre.kotlin.cycle.example.hello.HelloCycleActivity
import com.importre.kotlin.cycle.example.rest.UsersActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.reflect.KClass

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        hello.setOnClickListener { show(HelloActivity::class) }
        helloCyc.setOnClickListener { show(HelloCycleActivity::class) }
        bmi.setOnClickListener { show(BmiActivity::class) }
        rest.setOnClickListener { show(UsersActivity::class) }
        check.setOnClickListener { show(CheckActivity::class) }
    }

    private fun show(kClass: KClass<out Activity>) {
        startActivity(Intent(this, kClass.java))
    }
}
