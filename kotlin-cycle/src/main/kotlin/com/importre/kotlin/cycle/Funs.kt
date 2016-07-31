package com.importre.kotlin.cycle

import rx.Observable

inline fun cycle(init: KCycle.() -> Observable<() -> Unit>) {
    val c = KCycle()
    val view = c.init()
    val sinks = Sinks(DomSink(view, c.error))
    Cycle.run({ sinks }, c.dom)
}
