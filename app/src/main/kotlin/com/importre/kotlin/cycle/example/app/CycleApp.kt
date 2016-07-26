package com.importre.kotlin.cycle.example.app

import android.app.Application
import com.importre.kotlin.cycle.example.component.AppComponent
import com.importre.kotlin.cycle.example.component.DaggerAppComponent
import com.importre.kotlin.cycle.example.rest.module.ApiModule

class CycleApp : Application() {

    val comp: AppComponent by lazy {
        DaggerAppComponent.builder().apiModule(ApiModule(this)).build()
    }

    override fun onCreate() {
        super.onCreate()
    }
}
