package com.importre.kotlin.cycle.example.rest.module

import com.importre.kotlin.cycle.example.rest.util.IScheduler
import retrofit2.Call
import retrofit2.CallAdapter
import rx.Observable
import java.lang.reflect.Type

class ThreadCallAdapter(val adapter: CallAdapter<*>?,
                        val schedulers: IScheduler) : CallAdapter<Observable<*>> {

    override fun <R : Any?> adapt(call: Call<R>?): Observable<*>? {
        if (adapter == null) return null
        val adapt = adapter.adapt(call)
        if (adapt is Observable<*>) {
            return adapt
                    .subscribeOn(schedulers.background())
                    .observeOn(schedulers.main())
        }
        return null
    }

    override fun responseType(): Type? {
        return adapter?.responseType()
    }
}
