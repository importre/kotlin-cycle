package com.importre.kotlin.cycle

import rx.Observable

abstract class Driver(val name: String) {

    abstract fun imitate(stream: Observable<*>)
}
