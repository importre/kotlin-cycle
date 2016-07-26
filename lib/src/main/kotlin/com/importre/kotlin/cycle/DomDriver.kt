package com.importre.kotlin.cycle

import android.view.ViewGroup
import rx.Observable

class DomDriver(internal val root: ViewGroup) : Driver("DOM") {

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
