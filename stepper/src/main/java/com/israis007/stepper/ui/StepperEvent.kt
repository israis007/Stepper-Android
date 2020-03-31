package com.israis007.stepper.ui

import com.israis007.stepper.models.Step

interface StepperEvent {

    fun stepWillErase(step: Step)

    fun stepWillEdit(step: Step)

    fun confirmErase(): Boolean

    fun confirmEdit(): Step?

    fun stepClicked(step: Step)

}