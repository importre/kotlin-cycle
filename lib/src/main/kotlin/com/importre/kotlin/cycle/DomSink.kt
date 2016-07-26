package com.importre.kotlin.cycle

import rx.Observable

class DomSink(stream: Observable<() -> Unit>,
              error: ((error: Throwable) -> Unit)? = null) : Sink("DOM", stream, error)
