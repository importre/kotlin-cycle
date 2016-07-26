package com.importre.kotlin.cycle.example.rest.api

import com.importre.kotlin.cycle.example.rest.model.User
import retrofit2.http.GET
import rx.Observable

interface JsonPlaceholder {

    @GET("users")
    fun getUsers(): Observable<List<User>>
}
