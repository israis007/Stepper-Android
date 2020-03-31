package com.israis007.stepper.models

data class StepAttrs (
    val coorX: Float,
    val coorY: Float,
    val size: Float,
    val color: Int
) {
    fun getCoorYDrawLine(): Float = coorY + size - 5f
}