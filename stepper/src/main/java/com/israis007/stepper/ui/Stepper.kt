package com.israis007.stepper.ui

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.content.withStyledAttributes
import com.bumptech.glide.Glide
import com.israis007.stepper.R
import com.israis007.stepper.models.*
import com.israis007.stepper.tools.NumberHelper
import kotlin.math.abs

class Stepper @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private lateinit var atrib: AttrsStepper
    private var stepList = ArrayList<Step>()
    private var stepAttrsList = ArrayList<StepAttrs>()
    private val paint = Paint()
    private var aux = 0f
    private var stepperEvent: StepperEvent? = null

    init {
        context.withStyledAttributes(
            attrs,
            R.styleable.Stepper,
            defStyleAttr,
            R.style.StepperStyle
        ) {
            atrib = AttrsStepper()
            val reso = context.resources
            atrib.stepper_icon_edit = getDrawable(R.styleable.Stepper_stepper_icon_edit)
            atrib.stepper_icon_erase = getDrawable(R.styleable.Stepper_stepper_icon_erase)
            atrib.stepper_icon_done = getDrawable(R.styleable.Stepper_stepper_icon_done)
            atrib.stepper_icon_current = getDrawable(R.styleable.Stepper_stepper_icon_current)
            atrib.stepper_icon_error = getDrawable(R.styleable.Stepper_stepper_icon_error)
            atrib.stepper_icon_wait = getDrawable(R.styleable.Stepper_stepper_icon_wait)
            atrib.stepper_icon_color_done = getColor(
                R.styleable.Stepper_stepper_icon_color_done,
                ContextCompat.getColor(context, R.color.stepDone)
            )
            atrib.stepper_icon_color_current = getColor(
                R.styleable.Stepper_stepper_icon_color_done,
                ContextCompat.getColor(context, R.color.stepCurrent)
            )
            atrib.stepper_icon_color_error = getColor(
                R.styleable.Stepper_stepper_icon_color_done,
                ContextCompat.getColor(context, R.color.stepError)
            )
            atrib.stepper_icon_color_wait = getColor(
                R.styleable.Stepper_stepper_icon_color_done,
                ContextCompat.getColor(context, R.color.stepWait)
            )
            atrib.stepper_icon_color_edit = getColor(
                R.styleable.Stepper_stepper_icon_color_done,
                ContextCompat.getColor(context, R.color.stepIcons)
            )
            atrib.stepper_icon_color_erase = getColor(
                R.styleable.Stepper_stepper_icon_color_done,
                ContextCompat.getColor(context, R.color.stepIcons)
            )
            atrib.stepper_text_color_done = getColor(
                R.styleable.Stepper_stepper_icon_color_done,
                ContextCompat.getColor(context, R.color.stepDone)
            )
            atrib.stepper_text_color_current = getColor(
                R.styleable.Stepper_stepper_icon_color_done,
                ContextCompat.getColor(context, R.color.stepCurrent)
            )
            atrib.stepper_text_color_error = getColor(
                R.styleable.Stepper_stepper_icon_color_done,
                ContextCompat.getColor(context, R.color.stepError)
            )
            atrib.stepper_text_color_wait = getColor(
                R.styleable.Stepper_stepper_icon_color_done,
                ContextCompat.getColor(context, R.color.stepWait)
            )
            atrib.stepper_text_size = getDimension(
                R.styleable.Stepper_stepper_text_size,
                reso.getDimension(R.dimen.text_size)
            )
            atrib.stepper_sequence = AttrsStepper.getSequence(
                getInt(
                    R.styleable.Stepper_stepper_sequence,
                    reso.getInteger(R.integer.Sequence)
                )
            )
            atrib.stepper_line_width = getDimension(
                R.styleable.Stepper_stepper_line_width,
                reso.getDimension(R.dimen.line_witdh)
            )
            atrib.stepper_line_color_done = getColor(
                R.styleable.Stepper_stepper_line_color_done,
                ContextCompat.getColor(context, R.color.stepDone)
            )
            atrib.stepper_line_color_wait = getColor(
                R.styleable.Stepper_stepper_line_color_wait,
                ContextCompat.getColor(context, R.color.line_color_wait)
            )
            atrib.stepper_line_tintMode = AttrsStepper.getTintMode(
                getInt(
                    R.styleable.Stepper_stepper_line_tintMode,
                    reso.getInteger(R.integer.TintMode)
                )
            )
            atrib.stepper_space_between_steps = getDimension(
                R.styleable.Stepper_stepper_space_between_steps,
                reso.getDimension(R.dimen.step_distance)
            )

            atrib.stepper_icon_edit_will_tint =
                getBoolean(R.styleable.Stepper_stepper_icon_edit_will_tint, false)
            atrib.stepper_icon_erase_will_tint =
                getBoolean(R.styleable.Stepper_stepper_icon_erase_will_tint, false)
            atrib.stepper_icon_will_tint =
                getBoolean(R.styleable.Stepper_stepper_icon_will_tint, false)

            atrib.stepper_icon_size = getDimension(
                R.styleable.Stepper_stepper_icon_side_size,
                reso.getDimension(R.dimen.icon_side)
            )
            atrib.stepper_icon_edit_size = getDimension(
                R.styleable.Stepper_stepper_icon_edit_side_size,
                reso.getDimension(R.dimen.little_icon_side)
            )
            atrib.stepper_icon_erase_size = getDimension(
                R.styleable.Stepper_stepper_icon_erase_side_size,
                reso.getDimension(R.dimen.little_icon_side)
            )

            atrib.stepper_background = getColor(
                R.styleable.Stepper_stepper_background,
                ContextCompat.getColor(context, R.color.background)
            )

            /* Validating nulls */
            if (atrib.stepper_icon_edit == null)
                atrib.stepper_icon_edit = ContextCompat.getDrawable(context, R.drawable.ic_edit)

            if (atrib.stepper_icon_erase == null)
                atrib.stepper_icon_erase = ContextCompat.getDrawable(context, R.drawable.ic_erase)

            if (atrib.stepper_icon_done == null)
                atrib.stepper_icon_done = ContextCompat.getDrawable(context, R.drawable.ic_done)

            if (atrib.stepper_icon_current == null)
                atrib.stepper_icon_current =
                    ContextCompat.getDrawable(context, R.drawable.ic_current)

            if (atrib.stepper_icon_error == null)
                atrib.stepper_icon_error = ContextCompat.getDrawable(context, R.drawable.ic_error)

            if (atrib.stepper_icon_wait == null)
                atrib.stepper_icon_wait = ContextCompat.getDrawable(context, R.drawable.ic_wait)

            /* Creating a example array to draw in preview of editor */
            stepList.add(
                Step(
                    context,
                    1,
                    reso.getString(R.string.step1),
                    atrib.stepper_text_color_current,
                    reso.getString(R.string.step1),
                    atrib.stepper_text_color_done,
                    Status.DONE
                )
            )
            stepList.add(
                Step(
                    context,
                    2,
                    reso.getString(R.string.step2),
                    atrib.stepper_text_color_current,
                    reso.getString(R.string.step2),
                    atrib.stepper_text_color_done,
                    Status.ERROR
                )
            )
            stepList.add(
                Step(
                    context,
                    3,
                    reso.getString(R.string.step3),
                    atrib.stepper_text_color_current,
                    reso.getString(R.string.step3),
                    atrib.stepper_text_color_done,
                    Status.CURRENT
                )
            )
            stepList.add(
                Step(
                    context,
                    4,
                    reso.getString(R.string.step4),
                    atrib.stepper_text_color_current,
                    reso.getString(R.string.step4),
                    atrib.stepper_text_color_done,
                    Status.WAITING
                )
            )
            stepList.add(
                Step(
                    context,
                    5,
                    reso.getString(R.string.step5),
                    atrib.stepper_text_color_current,
                    reso.getString(R.string.step5),
                    atrib.stepper_text_color_done,
                    Status.WAITING
                )
            )

            orientation = VERTICAL

            setBackgroundColor(atrib.stepper_background)

            paintSteps()

        }
    }

    private fun paintSteps() {
        this.removeAllViews()

        stepAttrsList = ArrayList()

        aux = 0f

        for (i in 0 until stepList.size) {
            val step = stepList[i]
            val stepv = removeParent(createNewStep())
            val lp = LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
            )
            if (i != 0) {
                lp.setMargins(0, atrib.stepper_space_between_steps.toInt(), 0, 0)
            }
            stepv.layoutParams = lp

            /* Get views from card */
            val iconView = stepv.findViewById<ImageView>(R.id.icon_step)
            val iconEdit = stepv.findViewById<ImageView>(R.id.icon_edit)
            val iconErase = stepv.findViewById<ImageView>(R.id.icon_erase)
            val textView = stepv.findViewById<AppCompatTextView>(R.id.step_text)

            /* Set custom views */
            var iconfs: Drawable? = when (step.status) {
                Status.DONE -> step.iconDone
                Status.WAITING -> step.iconWait
                Status.CURRENT -> step.iconCurrent
                Status.ERROR -> step.iconError
            }

            if (iconfs == null)
                iconfs = when (step.status) {
                    Status.DONE -> atrib.stepper_icon_done
                    Status.WAITING -> atrib.stepper_icon_wait
                    Status.CURRENT -> atrib.stepper_icon_current
                    Status.ERROR -> atrib.stepper_icon_error
                }

            var iconErasefs: Drawable? = step.iconErase

            if (iconErasefs == null)
                iconErasefs = atrib.stepper_icon_erase

            var iconEditfs: Drawable? = step.iconEdit

            if (iconEditfs == null)
                iconEditfs = atrib.stepper_icon_edit


            Glide.with(context).load(iconfs).fitCenter().circleCrop().into(iconView)
            Glide.with(context).load(iconErasefs).fitCenter().circleCrop().into(iconErase)
            Glide.with(context).load(iconEditfs).fitCenter().circleCrop().into(iconEdit)

            textView.text = when (step.status) {
                Status.DONE -> step.textDone
                Status.WAITING -> step.textWait
                Status.CURRENT -> step.textCurrent
                Status.ERROR -> step.textError
            }

            var iconViewColor: Int? = when (step.status) {
                Status.DONE -> step.iconDoneColorTint
                Status.WAITING -> step.iconWaitColorTint
                Status.CURRENT -> step.iconCurrentColorTint
                Status.ERROR -> step.iconErrorColorTint
            }

            if (iconViewColor == null)
                iconViewColor = when (step.status) {
                    Status.DONE -> atrib.stepper_icon_color_done
                    Status.WAITING -> atrib.stepper_icon_color_wait
                    Status.CURRENT -> atrib.stepper_icon_color_current
                    Status.ERROR -> atrib.stepper_icon_color_error
                }

            var iconEraseColor: Int? = step.iconEraseColorTint

            if (iconEraseColor == null)
                iconEraseColor = atrib.stepper_icon_color_erase

            var iconEditColor: Int? = step.iconEditColorTint

            if (iconEditColor == null)
                iconEditColor = atrib.stepper_icon_color_erase

            if (atrib.stepper_icon_will_tint)
                iconView.setColorFilter(iconViewColor, android.graphics.PorterDuff.Mode.SRC_IN)

            if (atrib.stepper_icon_edit_will_tint)
                iconEdit.setColorFilter(iconEraseColor, android.graphics.PorterDuff.Mode.SRC_IN)

            if (atrib.stepper_icon_erase_will_tint)
                iconErase.setColorFilter(iconEditColor, android.graphics.PorterDuff.Mode.SRC_IN)

            var textColor: Int? = when (step.status) {
                Status.DONE -> step.textDoneColor
                Status.WAITING -> step.textWaitColor
                Status.CURRENT -> step.textCurrentColor
                Status.ERROR -> step.textErrorColor
            }

            if (textColor == null)
                textColor = when (step.status) {
                    Status.DONE -> atrib.stepper_text_color_done
                    Status.WAITING -> atrib.stepper_text_color_wait
                    Status.CURRENT -> atrib.stepper_text_color_current
                    Status.ERROR -> atrib.stepper_text_color_error
                }

            textView.setTextColor(textColor)
            textView.textSize = atrib.stepper_text_size
            val marg = context.resources.getDimension(R.dimen.icon_margin)

            aux += if (i == 0) marg else marg * 2 + atrib.stepper_icon_size + atrib.stepper_space_between_steps

            stepAttrsList.add(
                StepAttrs(
                    marg,
                    aux,
                    atrib.stepper_icon_size,
                    iconViewColor
                )
            )

            /* Add Events */
            if (stepperEvent != null) {
                iconErase.setOnClickListener {
                    if (step.isErasable) {
                        stepperEvent!!.stepWillErase(step)
                        if (stepperEvent!!.confirmErase()) {
                            stepList.remove(step)
                            paintSteps()
                        }
                    }
                }

                iconEdit.setOnClickListener {
                    if (step.isEditable) {
                        stepperEvent!!.stepWillEdit(step)
                        val stepTemp = stepperEvent!!.confirmEdit()
                        if (stepTemp != null) {
                            stepList.remove(step)
                            stepList.add(i, stepTemp)
                            paintSteps()
                        }
                    }
                }

                textView.setOnClickListener {
                    if (step.isEnabled)
                        stepperEvent!!.stepClicked(step)
                }
            }

            this.addView(stepv)
        }

    }

    private fun createNewStep(): View =
        LayoutInflater.from(context).inflate(R.layout.item_layout, null, false)

    private fun removeParent(view: View): View {
        if (view.parent != null)
            (view as ViewGroup).removeView(view)
        return view
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (canvas == null)
            return

        for (i in 0 until stepList.size - 1) {
            val nextStep = stepList[i + 1]
            val stepAttr = stepAttrsList[i]
            val nextStepAttr = stepAttrsList[i + 1]
            var half = NumberHelper.getHalfNumber(atrib.stepper_line_width) * -1

            when (atrib.stepper_line_tintMode) {
                TintMode.SOLID -> paint.color =
                    if (nextStep.status != Status.WAITING) atrib.stepper_line_color_done!! else atrib.stepper_line_color_wait!!
                TintMode.DEGRADED -> paint.shader = LinearGradient(
                    0f,
                    0f,
                    0f,
                    atrib.stepper_space_between_steps * .5f,
                    stepAttr.color,
                    nextStepAttr.color,
                    Shader.TileMode.MIRROR
                )
                TintMode.NONE -> {
                }
            }

            if (atrib.stepper_line_tintMode != TintMode.NONE)
                repeat(abs(half * 2 + 1)) {
                    canvas.drawLine(
                        half + stepAttr.coorX + stepAttr.size / 2,
                        stepAttr.getCoorYDrawLine(),
                        half + nextStepAttr.coorX + nextStepAttr.size / 2,
                        nextStepAttr.coorY,
                        paint
                    )
                    half++
                }

        }
    }

    fun setStepperEventListener(stepperEvent: StepperEvent) {
        this.stepperEvent = stepperEvent
        paintSteps()
    }

    fun setSteps(stepList: ArrayList<Step>) {
        this.stepList = stepList
        paintSteps()
    }

    fun getSteps(): ArrayList<Step> = this.stepList

    fun notifyDataChange() = paintSteps()
}