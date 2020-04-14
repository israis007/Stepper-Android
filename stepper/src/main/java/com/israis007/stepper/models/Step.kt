package com.israis007.stepper.models

import android.graphics.drawable.Drawable
import com.israis007.stepper.R

data class Step(
    var idStep: Int = 0,
    var iconWait: Drawable? = null,
    var iconDone: Drawable? = null,
    var iconError: Drawable? = null,
    var iconCurrent: Drawable? = null,
    var iconEdit: Drawable? = null,
    var iconErase: Drawable? = null,
    var iconWaitColorTint: Int? = null,
    var iconDoneColorTint: Int? = null,
    var iconErrorColorTint: Int? = null,
    var iconCurrentColorTint: Int? = null,
    var iconEditColorTint: Int? = null,
    var iconEraseColorTint: Int? = null,
    var textWait: String = "",
    var textDone: String = "",
    var textError: String = "",
    var textCurrent: String = "",
    var textWaitColor: Int? = null,
    var textDoneColor: Int? = null,
    var textErrorColor: Int? = null,
    var textCurrentColor: Int? = null,
    var status: Status = Status.WAITING,
    var isEditable: Boolean = true,
    var isErasable: Boolean = true,
    var isEnabled: Boolean = true
) {
    constructor(idStep: Int, textCurrent: String, status: Status) : this(
        idStep = idStep,
        iconWait = null,
        iconDone = null,
        iconError = null,
        iconCurrent = null,
        iconEdit = null,
        iconErase = null,
        iconWaitColorTint = null,
        iconDoneColorTint = null,
        iconErrorColorTint = null,
        iconCurrentColorTint = null,
        iconEditColorTint = null,
        iconEraseColorTint = null,
        textWait = textCurrent,
        textDone = textCurrent,
        textError = textCurrent,
        textCurrent = textCurrent,
        textWaitColor = null,
        textDoneColor = null,
        textErrorColor = null,
        textCurrentColor = null,
        status = status
    )

    constructor(
        idStep: Int,
        textCurrent: String,
        textCurrentColor: Int,
        textDone: String,
        textDoneColor: Int,
        status: Status
    ) : this(
        idStep = idStep,
        iconWait = null,
        iconDone = null,
        iconError = null,
        iconCurrent = null,
        iconEdit = null,
        iconErase = null,
        iconWaitColorTint = null,
        iconDoneColorTint = null,
        iconErrorColorTint = null,
        iconCurrentColorTint = null,
        iconEditColorTint = null,
        iconEraseColorTint = null,
        textWait = textCurrent,
        textDone = textDone,
        textError = textCurrent,
        textCurrent = textCurrent,
        textWaitColor = null,
        textDoneColor = textDoneColor,
        textErrorColor = null,
        textCurrentColor = textCurrentColor,
        status = status
    )
}

enum class Status(val value: Int, val color: Int, val icon: Int) {
    WAITING(0, R.color.stepWait, R.drawable.ic_wait),
    DONE(1, R.color.stepDone, R.drawable.ic_done),
    ERROR(2, R.color.stepError, R.drawable.ic_error),
    CURRENT(3, R.color.stepCurrent, R.drawable.ic_current);
}