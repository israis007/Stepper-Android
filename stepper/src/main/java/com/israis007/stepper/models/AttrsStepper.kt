package com.israis007.stepper.models

import android.graphics.drawable.Drawable

class AttrsStepper {
    var stepper_icon_edit: Drawable? = null
    var stepper_icon_erase: Drawable? = null
    var stepper_icon_done: Drawable? = null
    var stepper_icon_current: Drawable? = null
    var stepper_icon_error: Drawable? = null
    var stepper_icon_wait: Drawable? = null
    var stepper_icon_color_done: Int? = null
    var stepper_icon_color_current: Int? = null
    var stepper_icon_color_error: Int? = null
    var stepper_icon_color_wait: Int? = null
    var stepper_icon_color_edit: Int? = null
    var stepper_icon_color_erase: Int? = null
    var stepper_text_color_done: Int? = null
    var stepper_text_color_current: Int? = null
    var stepper_text_color_error: Int? = null
    var stepper_text_color_wait: Int? = null
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
    var stepper_new_border_enable_color: Int = 0
    var stepper_new_border_disable_color: Int = 0
    var stepper_new_text_color: Int = 0
    var stepper_new_text_size: Float = 0f
    var stepper_new_button_text_size: Float = 0f
    var stepper_new_button_allcaps: Boolean = false
    var stepper_new_margin_start: Float = 0f
    var stepper_new_margin_top: Float = 0f
    var stepper_new_margin_end: Float = 0f
    var stepper_new_margin_bottom: Float = 0f
    var stepper_text: String  =""
    var stepper_text_button: String = ""
    var stepper_text_type: Texts = Texts.SAME
    var stepper_text_done: String = ""
    var stepper_text_error: String = ""


    companion object {
        fun getSequence(valor: Int) = if (valor == 0) Sequence.SEQUENTIAL else Sequence.NON_SEQUENTIAL

        fun getTintMode(valor: Int) = when(valor) {
            0 -> TintMode.NONE
            1 -> TintMode.DEGRADED
            else -> TintMode.SOLID
        }

        fun getTexts(valor: Int) = when(valor) {
            0 -> Texts.CUSTOM
            else -> Texts.SAME
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

enum class Texts{
    CUSTOM,
    SAME
}