package com.importre.kotlin.cycle

import android.support.annotation.IdRes
import android.view.View
import rx.Observable

class DomSource(internal val driver: DomDriver) : Source("DOM") {

    override fun imitate(stream: Observable<*>) {
        driver.imitate(stream)
    }

    fun select(@IdRes id: Int): DomSelection {
        val view = driver.root.findViewById(id)
        return DomSelection(view)
    }

    fun select(view: View): DomSelection {
        return DomSelection(view)
    }
}

