package com.israis007.stepper.models

import android.graphics.drawable.Drawable

class AttrsStepper {
    var stepper_icon_edit: Drawable? = null
    var stepper_icon_erase: Drawable? = null
    var stepper_icon_done: Drawable? = null
    var stepper_icon_current: Drawable? = null
    var stepper_icon_error: Drawable? = null
    var stepper_icon_wait: Drawable? = null
    var stepper_icon_color_done: Int = 0
    var stepper_icon_color_current: Int = 0
    var stepper_icon_color_error: Int = 0
    var stepper_icon_color_wait: Int = 0
    var stepper_icon_color_edit: Int = 0
    var stepper_icon_color_erase: Int = 0
    var stepper_text_color_done: Int = 0
    var stepper_text_color_current: Int = 0
    var stepper_text_color_error: Int = 0
    var stepper_text_color_wait: Int = 0
    var stepper_text_size: Float = 0f
    var stepper_sequence: Sequence = Sequence.SEQUENTIAL
    var stepper_line_width: Float = 0f
    var stepper_line_color_done: Int? = null
    var stepper_line_color_wait: Int? = null
    var stepper_line_tintMode: TintMode = TintMode.SOLID
    var stepper_space_between_steps: Float = 0f
    var stepper_icon_edit_will_tint: Boolean = true
    var stepper_icon_erase_will_tint: Boolean = true
    var stepper_icon_will_tint: Boolean = true
    var stepper_icon_size: Float = 0f
    var stepper_icon_edit_size: Float = 0f
    var stepper_icon_erase_size: Float = 0f
    var stepper_background: Int = 0

    companion object {
        fun getSequence(valor: Int) = if (valor == 0) Sequence.SEQUENTIAL else Sequence.NON_SEQUENTIAL

        fun getTintMode(valor: Int) = when(valor) {
            0 -> TintMode.NONE
            1 -> TintMode.DEGRADED
            else -> TintMode.SOLID
        }
    }
}

enum class Sequence {
    SEQUENTIAL,
    NON_SEQUENTIAL
}

enum class TintMode{
    NONE,
    DEGRADED,
    SOLID
}