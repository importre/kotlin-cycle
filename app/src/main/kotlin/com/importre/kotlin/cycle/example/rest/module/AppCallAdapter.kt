package com.importre.kotlin.cycle.example.rest.module

import com.importre.kotlin.cycle.example.rest.util.IScheduler
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import rx.Observable
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.lang.reflect.Type

class AppCallAdapter(val schedulers: IScheduler) : CallAdapter.Factory() {

    companion object {
        fun create(): AppCallAdapter {
            return AppCallAdapter(object : IScheduler {
                override fun main(): Scheduler = AndroidSchedulers.mainThread()
                override fun background(): Scheduler = Schedulers.io()
            })
        }
    }

    override fun get(returnType: Type,
                     annotations: Array<Annotation>,
                     retrofit: Retrofit): CallAdapter<Observable<*>>? {
        val rxFactory = RxJavaCallAdapterFactory.create()
        val adapter = rxFactory.get(returnType, annotations, retrofit)
        return ThreadCallAdapter(adapter as? CallAdapter<*>?, schedulers)
    }
}
