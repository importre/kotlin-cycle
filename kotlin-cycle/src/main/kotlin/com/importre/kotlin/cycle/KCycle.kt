package com.importre.kotlin.cycle

class KCycle(val dom: DomSource = DomSource()) {
    var error: ((error: Throwable) -> Unit?)? = null
}
