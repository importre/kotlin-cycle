package com.importre.kotlin.cycle.example.rest.module

import android.content.Context
import com.importre.kotlin.cycle.example.BuildConfig
import com.importre.kotlin.cycle.example.rest.api.JsonPlaceholder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule(val context: Context) {

    @Provides
    @Singleton
    fun provideContext() = context

    @Provides
    @Singleton
    fun provideOkHttpClient() = OkHttpClient.Builder()
            .addInterceptor(initHttpLoggingInterceptor())
            .build() ?: OkHttpClient()

    private fun initHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }

    @Provides
    @Singleton
    fun provideApiService(client: OkHttpClient): JsonPlaceholder {
        val endpoint = "http://jsonplaceholder.typicode.com"
        val retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(endpoint)
                .addCallAdapterFactory(AppCallAdapter.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit.create(JsonPlaceholder::class.java)
    }
}
