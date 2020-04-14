package com.israis007.stepper.ui

import com.israis007.stepper.models.Step

interface StepperEvent {

    fun stepWillErase(step: Step)

    fun stepWillEdit(step: Step)

    fun stepClicked(step: Step)

    fun stepAdded(step: Step)

}