package com.importre.kotlin.cycle

import rx.Observable

inline fun cycle(init: Cycle.() -> Observable<() -> Unit>) {
    val c = Cycle()
    val view = c.init()
    val sinks = Sinks(DomSink(view, c.error))
    Cycle.run({ sinks }, c.dom)
}
