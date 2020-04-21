package com.example.stepview_demo2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.shuhart.stepview.StepView


class MainActivity : AppCompatActivity() {

    private var stepView: StepView? = null
    private var button: Button? = null

    var step = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        stepView = findViewById(R.id.id_stepView)
        button = findViewById(R.id.id_nextButton)

        button?.setOnClickListener {

            stepView?.done(true)
            stepView?.state
                ?.nextStepCircleEnabled(true)
                ?.steps(resources.getStringArray(R.array.userDetailStep).toMutableList())
                ?.commit()

            stepView?.go(++step,true)

        }
    }
}