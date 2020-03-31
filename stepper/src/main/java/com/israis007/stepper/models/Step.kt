package com.israis007.stepper.models

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat
import com.israis007.stepper.R

data class Step(
    @NonNull
    var context: Context,
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
    init {
        iconWait = ContextCompat.getDrawable(context, Status.WAITING.icon)
        iconDone = ContextCompat.getDrawable(context, Status.DONE.icon)
        iconError = ContextCompat.getDrawable(context, Status.ERROR.icon)
        iconCurrent = ContextCompat.getDrawable(context, Status.CURRENT.icon)
        iconEdit = ContextCompat.getDrawable(context, R.drawable.ic_edit)
        iconErase = ContextCompat.getDrawable(context, R.drawable.ic_erase)
        iconWaitColorTint = ContextCompat.getColor(context, Status.WAITING.color)
        iconDoneColorTint = ContextCompat.getColor(context, Status.DONE.color)
        iconErrorColorTint = ContextCompat.getColor(context, Status.ERROR.color)
        iconCurrentColorTint = ContextCompat.getColor(context, Status.CURRENT.color)
        iconEditColorTint = ContextCompat.getColor(context, R.color.stepIcons)
        iconEraseColorTint = ContextCompat.getColor(context, R.color.stepIcons)
        textWaitColor = ContextCompat.getColor(context, Status.WAITING.color)
        textDoneColor = ContextCompat.getColor(context, Status.DONE.color)
        textErrorColor = ContextCompat.getColor(context, Status.ERROR.color)
        textCurrentColor = ContextCompat.getColor(context, Status.CURRENT.color)
    }

    constructor(context: Context, idStep: Int, textCurrent: String, status: Status) : this(
        context = context,
        idStep = idStep,
        iconWait = ContextCompat.getDrawable(context, Status.WAITING.icon),
        iconDone = ContextCompat.getDrawable(context, Status.DONE.icon),
        iconError = ContextCompat.getDrawable(context, Status.ERROR.icon),
        iconCurrent = ContextCompat.getDrawable(context, Status.CURRENT.icon),
        iconEdit = ContextCompat.getDrawable(context, R.drawable.ic_edit),
        iconErase = ContextCompat.getDrawable(context, R.drawable.ic_erase),
        iconWaitColorTint = ContextCompat.getColor(context, Status.WAITING.color),
        iconDoneColorTint = ContextCompat.getColor(context, Status.DONE.color),
        iconErrorColorTint = ContextCompat.getColor(context, Status.ERROR.color),
        iconCurrentColorTint = ContextCompat.getColor(context, Status.CURRENT.color),
        iconEditColorTint = ContextCompat.getColor(context, R.color.stepIcons),
        iconEraseColorTint = ContextCompat.getColor(context, R.color.stepIcons),
        textWait = textCurrent,
        textDone = textCurrent,
        textError = textCurrent,
        textCurrent = textCurrent,
        textWaitColor = ContextCompat.getColor(context, Status.WAITING.color),
        textDoneColor = ContextCompat.getColor(context, Status.DONE.color),
        textErrorColor = ContextCompat.getColor(context, Status.ERROR.color),
        textCurrentColor = ContextCompat.getColor(context, Status.CURRENT.color),
        status = status
    )

    constructor(
        context: Context,
        idStep: Int,
        textCurrent: String,
        textCurrentColor: Int,
        textDone: String,
        textDoneColor: Int,
        status: Status
    ) : this(
        context = context,
        idStep = idStep,
        iconWait = ContextCompat.getDrawable(context, Status.WAITING.icon),
        iconDone = ContextCompat.getDrawable(context, Status.DONE.icon),
        iconError = ContextCompat.getDrawable(context, Status.ERROR.icon),
        iconCurrent = ContextCompat.getDrawable(context, Status.CURRENT.icon),
        iconEdit = ContextCompat.getDrawable(context, R.drawable.ic_edit),
        iconErase = ContextCompat.getDrawable(context, R.drawable.ic_erase),
        iconWaitColorTint = ContextCompat.getColor(context, Status.WAITING.color),
        iconDoneColorTint = ContextCompat.getColor(context, Status.DONE.color),
        iconErrorColorTint = ContextCompat.getColor(context, Status.ERROR.color),
        iconCurrentColorTint = ContextCompat.getColor(context, Status.CURRENT.color),
        iconEditColorTint = ContextCompat.getColor(context, R.color.stepIcons),
        iconEraseColorTint = ContextCompat.getColor(context, R.color.stepIcons),
        textWait = textCurrent,
        textDone = textDone,
        textError = textCurrent,
        textCurrent = textCurrent,
        textWaitColor = ContextCompat.getColor(context, Status.WAITING.color),
        textDoneColor = textDoneColor,
        textErrorColor = ContextCompat.getColor(context, Status.ERROR.color),
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