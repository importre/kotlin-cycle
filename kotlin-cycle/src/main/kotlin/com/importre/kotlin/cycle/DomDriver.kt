package com.importre.kotlin.cycle

import rx.Observable

class DomDriver() : Driver("DOM") {

    override fun imitate(stream: Observable<*>) {
        stream.subscribe({
            if (it is Function0<*>) {
                it.invoke()
            }
        }, { error ->
            error.printStackTrace()
        })
    }
}
