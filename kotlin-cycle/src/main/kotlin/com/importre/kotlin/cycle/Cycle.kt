package com.importre.kotlin.cycle

import rx.subjects.ReplaySubject
import java.util.*

object Cycle {

    val name = "kotlin-cycle"

    @JvmStatic
    fun run(mainFun: (Sources) -> Sinks, vararg sources: Source) {
        val sourceList = sources.toList()
        val proxySinks = createProxySinks(sourceList)
        val sinks = mainFun.invoke(Sources(sourceList))
        sinks.forEach { sink ->
            val proxy = proxySinks[sink.name]
            sink.stream.subscribe({
                proxy?.onNext(it)
            }, {
                sink.error?.invoke(it)
            }, {
                proxy?.onCompleted()
            })
        }
    }

    private fun createProxySinks(sources: List<Source>): HashMap<String, ReplaySubject<Any>> {
        val proxySinks = hashMapOf<String, ReplaySubject<Any>>()
        sources.forEach { source ->
            val proxySink = ReplaySubject.create<Any>()
            proxySinks.put(source.name, proxySink)
            source.imitate(proxySink)
        }
        return proxySinks
    }
}
