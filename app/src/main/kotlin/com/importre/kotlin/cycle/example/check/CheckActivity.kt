package com.importre.kotlin.cycle.example.check

import android.os.Bundle
import com.importre.kotlin.cycle.*
import com.importre.kotlin.cycle.example.BaseActivity
import com.importre.kotlin.cycle.example.R
import kotlinx.android.synthetic.main.activity_check.*

class CheckActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check)

        Cycle.run(main, DomSource(DomDriver(root)))
    }

    private val main = { sources: Sources ->
        val change = sources.dom()
                .select(toggle.apply { isChecked = true })
                .checkedChanges()
        val model = change
                .map { onOff -> "안드로이드 개발자를 모십니다: $onOff" }
                .map { recruit -> "Riiid!\n$recruit" }
        val view = model.map { message -> onUpdateView(message) }
        Sinks(DomSink(view))
    }

    private fun onUpdateView(message: CharSequence) = {
        checkText.text = message
    }
}
