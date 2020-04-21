package com.example.stepview_demo2

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.content.ContextCompat
import com.baoyachi.stepview.HorizontalStepView
import com.baoyachi.stepview.bean.StepBean

class MainActivity : AppCompatActivity() {

    private var stepView : HorizontalStepView? = null
    private var button : Button? = null
    private var stepList = ArrayList<StepBean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        stepView = findViewById(R.id.id_stepView)
        button = findViewById(R.id.id_nextButton)


        stepList.add(StepBean("Step-1",-1))
        stepList.add(StepBean("Step-2",-1))
        stepList.add(StepBean("Step-3",-1))
        stepList.add(StepBean("Step-4",-1))
        stepList.add(StepBean("Step-5",-1))

        updateSteps(0,-1)
        var id = 0
        button?.setOnClickListener {
            id++
            Log.e("AXE","----->  $id")
            when(id){
                1 -> updateSteps(0,1)
                2 -> updateSteps(1,1)
                3 -> updateSteps(2,1)
                4 -> updateSteps(3,1)
                5 -> updateSteps(4,1)


            }
        }
    }

    private fun updateSteps(index : Int,state : Int){


        stepList[index].state = state

        stepView?.setTextSize(10)
        stepView?.setStepViewTexts(stepList)
        stepView?.setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(this,R.color.red))
        stepView?.setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(this,R.drawable.default_icon))
        stepView?.setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(this,R.color.green))
        stepView?.setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(this,R.drawable.complted))
        stepView?.setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(this,R.drawable.attention))
        stepView?.setStepViewUnComplectedTextColor(ContextCompat.getColor(this,R.color.blue))
        stepView?.setStepViewComplectedTextColor(ContextCompat.getColor(this,R.color.green))

    }

}