package com.importre.kotlin.cycle

import android.view.View
import rx.Observable

class DomSource @JvmOverloads constructor(val driver: DomDriver = DomDriver()) : Source("DOM") {

    override fun imitate(stream: Observable<*>) {
        driver.imitate(stream)
    }

    fun select(view: View): DomSelection {
        return DomSelection(view)
    }
}

