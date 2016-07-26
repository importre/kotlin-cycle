package com.importre.kotlin.cycle.example.component

import com.importre.kotlin.cycle.example.rest.UsersActivity
import com.importre.kotlin.cycle.example.rest.module.ApiModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApiModule::class))
interface AppComponent {

    fun inject(activity: UsersActivity)
}
