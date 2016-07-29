package com.importre.kotlin.cycle

import java.util.*

class Sources(sourceList: List<Source>) : HashMap<String, Source>() {

    init {
        putAll(sourceList.map { source ->
            source.name to source
        })
    }

    val dom: DomSource = get("DOM") as DomSource
}
