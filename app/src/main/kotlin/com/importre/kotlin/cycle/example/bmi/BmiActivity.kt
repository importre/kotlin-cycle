package com.importre.kotlin.cycle.example.bmi

import android.os.Bundle
import com.importre.kotlin.cycle.*
import com.importre.kotlin.cycle.example.BaseActivity
import com.importre.kotlin.cycle.example.R
import kotlinx.android.synthetic.main.activity_bmi.*
import rx.Observable

class BmiActivity : BaseActivity() {

    data class State(val weight: Int, val height: Int, val bmi: Int)
    data class Props(val min: Int, val max: Int, val value: Int)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi)

        Cycle.run(main, DomSource(DomDriver(root)))
    }

    private val main = { sources: Sources ->
        val dom = sources.dom
        val weightProps = Observable.just(Props(min = 40, max = 140, value = 70))
        val heightProps = Observable.just(Props(min = 140, max = 210, value = 170))

        // Intent
        val (weightChangeStream, heightChangeStream) = intent(dom, heightProps, weightProps)

        // Model
        val stateStream = model(weightChangeStream, heightChangeStream, weightProps, heightProps)

        // View
        val viewStream = stateStream.map { state -> onUpdateView(state) }

        Sinks(DomSink(viewStream))
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
        Observable.combineLatest(weightChangeStream, heightChangeStream,
                weightProps, heightProps) { weight, height, weightProps, heightProps ->
            val realHeight = height + heightProps.min
            val realWeight = weight + weightProps.min
            val heightMeters = realHeight * 0.01F
            val bmi = Math.round(realWeight / (heightMeters * heightMeters))
            State(realWeight, realHeight, bmi)
        }
    }

    private fun onUpdateView(state: State) = {
        weightText.text = "Weight: ${state.weight} kg"
        heightText.text = "Height: ${state.height} cm"
        bmiText.text = "Bmi: ${state.bmi}"
    }
}
