package com.importre.kotlin.cycle

import org.junit.Test
import rx.Observable
import rx.observers.TestSubscriber

class CycleTest {

    companion object {
        const val name = "test"
    }

    class TempSource : Source(name) {

        var stream: Observable<*>? = null

        override fun imitate(stream: Observable<*>) {
            this.stream = stream
            stream.subscribe()
        }
    }

    @Test
    fun testRun() {
        val main = { sources: Sources ->
            val stream = Observable.just("cycle")
            Sinks(Sink(name, stream))
        }

        val source = TempSource()
        Cycle.run(main, source)

        val test = TestSubscriber<Any>()
        source.stream?.subscribe(test)
        test.assertValue("cycle")
        test.assertNoErrors()
        test.assertCompleted()
    }
}
