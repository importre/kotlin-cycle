package com.importre.kotlin.cycle

import rx.Observable

abstract class Source(val name: String) {

    abstract fun imitate(stream: Observable<*>)
}
