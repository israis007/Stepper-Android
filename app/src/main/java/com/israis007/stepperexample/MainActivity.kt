package com.israis007.stepperexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.israis007.stepper.models.Step
import com.israis007.stepper.ui.StepperEvent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val TAG = "MainActivity"
        val lista = stepper.getSteps()
        lista.removeAt(1)

        stepper.setStepperEventListener(object: StepperEvent{
            override fun stepWillErase(step: Step) {
                Log.d(TAG, "Step erase ${step.textCurrent} -> ${step.idStep}")
            }

            override fun stepWillEdit(step: Step) {
                Log.d(TAG, "Step edit ${step.textCurrent} -> ${step.idStep}")
            }

            override fun stepClicked(step: Step) {
                Log.d(TAG, "Step clicked ${step.textCurrent} -> ${step.idStep}")
            }

            override fun stepAdded(step: Step) {
                Log.d(TAG, "Step added ${step.textCurrent} -> ${step.idStep}")
            }
        })

        stepper.notifyDataChange()
    }
}
