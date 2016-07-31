package com.importre.kotlin.cycle.example.bmi

import android.os.Bundle
import com.importre.kotlin.cycle.DomSource
import com.importre.kotlin.cycle.cycle
import com.importre.kotlin.cycle.example.BaseActivity
import com.importre.kotlin.cycle.example.R
import com.importre.kotlin.cycle.example.ext.toast
import kotlinx.android.synthetic.main.activity_bmi.*
import rx.Observable
import unwrap

class BmiActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi)

        cycle {
            val weightProps = Observable.just(Props(min = 40, max = 140, value = 70))
            val heightProps = Observable.just(Props(min = 140, max = 210, value = 170))

            // Intent
            val (weightStream, heightStream) = intent(dom, heightProps, weightProps)

            // Model
            val stateStream = model(weightStream, heightStream, weightProps, heightProps)

            // View
            stateStream.map { state -> onUpdateView(state) }
        }
    }

    private val intent = { dom: DomSource,
                           heightProps: Observable<Props>,
                           weightProps: Observable<Props> ->
        val weightChangeStream = dom
                .select(weightSeekBar.apply {
                    val props = weightProps.toBlocking().first()
                    max = props.max - props.min
                    progress = props.value - props.min
                })
                .userChanges()

        val heightChangeStream = dom
                .select(heightSeekBar.apply {
                    val props = heightProps.toBlocking().first()
                    max = props.max - props.min
                    progress = props.value - props.min
                })
                .userChanges()
        Pair(weightChangeStream, heightChangeStream)
    }

    private val model = { weightChangeStream: Observable<Int>,
                          heightChangeStream: Observable<Int>,
                          weightProps: Observable<Props>,
                          heightProps: Observable<Props> ->
        Observable.combineLatest(
                weightChangeStream, heightChangeStream,
                weightProps, heightProps,
                ::calculateBmi)
    }

    private fun onUpdateView(state: State) = {
        unwrap(weightText, heightText, bmiText) { w, h, b ->
            w.text = "Weight: ${state.weight} kg"
            h.text = "Height: ${state.height} cm"
            b.text = "Bmi: ${state.bmi}"
        } nah {
            toast(R.string.error_undefined_views)
        }
    }
}
