package com.israis007.stepper.ui

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.*
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.content.withStyledAttributes
import com.bumptech.glide.Glide
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.israis007.stepper.R
import com.israis007.stepper.models.*
import com.israis007.stepper.tools.NumberHelper
import com.israis007.stepper.tools.ViewTools
import kotlin.collections.ArrayList
import kotlin.math.abs

class Stepper @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private lateinit var attrsStepper: AttrsStepper
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
            attrsStepper = AttrsStepper()
            val reso = context.resources
            attrsStepper.stepper_icon_edit = getDrawable(R.styleable.Stepper_stepper_icon_edit)
            attrsStepper.stepper_icon_erase = getDrawable(R.styleable.Stepper_stepper_icon_erase)
            attrsStepper.stepper_icon_done = getDrawable(R.styleable.Stepper_stepper_icon_done)
            attrsStepper.stepper_icon_current = getDrawable(R.styleable.Stepper_stepper_icon_current)
            attrsStepper.stepper_icon_error = getDrawable(R.styleable.Stepper_stepper_icon_error)
            attrsStepper.stepper_icon_wait = getDrawable(R.styleable.Stepper_stepper_icon_wait)
            attrsStepper.stepper_icon_color_done = getColor(
                R.styleable.Stepper_stepper_icon_color_done,
                ContextCompat.getColor(context, R.color.stepDone)
            )
            attrsStepper.stepper_icon_color_current = getColor(
                R.styleable.Stepper_stepper_icon_color_current,
                ContextCompat.getColor(context, R.color.stepCurrent)
            )
            attrsStepper.stepper_icon_color_error = getColor(
                R.styleable.Stepper_stepper_icon_color_error,
                ContextCompat.getColor(context, R.color.stepError)
            )
            attrsStepper.stepper_icon_color_wait = getColor(
                R.styleable.Stepper_stepper_icon_color_wait,
                ContextCompat.getColor(context, R.color.stepWait)
            )
            attrsStepper.stepper_icon_color_edit = getColor(
                R.styleable.Stepper_stepper_icon_color_edit,
                ContextCompat.getColor(context, R.color.stepIcons)
            )
            attrsStepper.stepper_icon_color_erase = getColor(
                R.styleable.Stepper_stepper_icon_color_erase,
                ContextCompat.getColor(context, R.color.stepIcons)
            )
            attrsStepper.stepper_text_color_done = getColor(
                R.styleable.Stepper_stepper_icon_color_done,
                ContextCompat.getColor(context, R.color.stepDone)
            )
            attrsStepper.stepper_text_color_current = getColor(
                R.styleable.Stepper_stepper_icon_color_current,
                ContextCompat.getColor(context, R.color.stepCurrent)
            )
            attrsStepper.stepper_text_color_error = getColor(
                R.styleable.Stepper_stepper_icon_color_error,
                ContextCompat.getColor(context, R.color.stepError)
            )
            attrsStepper.stepper_text_color_wait = getColor(
                R.styleable.Stepper_stepper_icon_color_wait,
                ContextCompat.getColor(context, R.color.stepWait)
            )
            attrsStepper.stepper_text_size = getDimension(
                R.styleable.Stepper_stepper_text_size,
                reso.getDimension(R.dimen.text_size)
            )
            attrsStepper.stepper_sequence = AttrsStepper.getSequence(
                getInt(
                    R.styleable.Stepper_stepper_sequence,
                    reso.getInteger(R.integer.Sequence)
                )
            )
            attrsStepper.stepper_line_width = getDimension(
                R.styleable.Stepper_stepper_line_width,
                reso.getDimension(R.dimen.line_witdh)
            )
            attrsStepper.stepper_line_color_done = getColor(
                R.styleable.Stepper_stepper_line_color_done,
                ContextCompat.getColor(context, R.color.stepDone)
            )
            attrsStepper.stepper_line_color_wait = getColor(
                R.styleable.Stepper_stepper_line_color_wait,
                ContextCompat.getColor(context, R.color.line_color_wait)
            )
            attrsStepper.stepper_line_tintMode = AttrsStepper.getTintMode(
                getInt(
                    R.styleable.Stepper_stepper_line_tintMode,
                    reso.getInteger(R.integer.TintMode)
                )
            )
            attrsStepper.stepper_space_between_steps = getDimension(
                R.styleable.Stepper_stepper_space_between_steps,
                reso.getDimension(R.dimen.step_distance)
            )

            attrsStepper.stepper_icon_edit_will_tint =
                getBoolean(R.styleable.Stepper_stepper_icon_edit_will_tint, false)
            attrsStepper.stepper_icon_erase_will_tint =
                getBoolean(R.styleable.Stepper_stepper_icon_erase_will_tint, false)
            attrsStepper.stepper_icon_will_tint =
                getBoolean(R.styleable.Stepper_stepper_icon_will_tint, false)

            attrsStepper.stepper_icon_size = getDimension(
                R.styleable.Stepper_stepper_icon_side_size,
                reso.getDimension(R.dimen.icon_side)
            )
            attrsStepper.stepper_icon_edit_size = getDimension(
                R.styleable.Stepper_stepper_icon_edit_side_size,
                reso.getDimension(R.dimen.little_icon_side)
            )
            attrsStepper.stepper_icon_erase_size = getDimension(
                R.styleable.Stepper_stepper_icon_erase_side_size,
                reso.getDimension(R.dimen.little_icon_side)
            )

            attrsStepper.stepper_background = getColor(
                R.styleable.Stepper_stepper_background,
                ContextCompat.getColor(context, R.color.background)
            )

            attrsStepper.stepper_new_border_enable_color = getColor(
                R.styleable.Stepper_stepper_new_border_enable_color,
                ContextCompat.getColor(context, R.color.primary)
            )
            attrsStepper.stepper_new_border_disable_color = getColor(
                R.styleable.Stepper_stepper_new_border_disable_color,
                ContextCompat.getColor(context, R.color.textColor)
            )
            attrsStepper.stepper_new_text_color = getColor(
                R.styleable.Stepper_stepper_new_text_color,
                ContextCompat.getColor(context, R.color.primary)
            )
            attrsStepper.stepper_new_text_size = getDimension(
                R.styleable.Stepper_stepper_new_text_size,
                reso.getDimension(R.dimen.detail_textsize)
            )
            attrsStepper.stepper_new_button_text_size = getDimension(
                R.styleable.Stepper_stepper_new_button_text_size,
                reso.getDimension(R.dimen.detail_textsize)
            )
            attrsStepper.stepper_new_button_allcaps =
                getBoolean(R.styleable.Stepper_stepper_new_button_allcaps, false)
            attrsStepper.stepper_new_margin_start = getDimension(
                R.styleable.Stepper_stepper_new_margin_start,
                reso.getDimension(R.dimen.note_margin_sides)
            )
            attrsStepper.stepper_new_margin_top = getDimension(
                R.styleable.Stepper_stepper_new_margin_top,
                reso.getDimension(R.dimen.note_margin_top)
            )
            attrsStepper.stepper_new_margin_end = getDimension(
                R.styleable.Stepper_stepper_new_margin_end,
                reso.getDimension(R.dimen.note_margin_sides)
            )
            attrsStepper.stepper_new_margin_bottom = getDimension(
                R.styleable.Stepper_stepper_new_margin_bottom,
                reso.getDimension(R.dimen.note_margin_bottom)
            )

            attrsStepper.stepper_text_type = AttrsStepper.getTexts(getInt(R.styleable.Stepper_stepper_texts, reso.getInteger(R.integer.TextsType)))

            val texte = getString(R.styleable.Stepper_stepper_text_error)
            attrsStepper.stepper_text_error = texte ?: reso.getString(R.string.step2)

            val textd = getString(R.styleable.Stepper_stepper_text_done)
            attrsStepper.stepper_text_done = textd ?: reso.getString(R.string.step1)

            val textn = getString(R.styleable.Stepper_stepper_text)
            attrsStepper.stepper_text = textn ?: reso.getString(R.string.add_new_activity)

            val textbn = getString(R.styleable.Stepper_stepper_text_button)
            attrsStepper.stepper_text_button = textbn ?: reso.getString(R.string.add_new_activity_button)

            /* Validating nulls */
            if (attrsStepper.stepper_icon_edit == null)
                attrsStepper.stepper_icon_edit = ContextCompat.getDrawable(context, R.drawable.ic_edit)

            if (attrsStepper.stepper_icon_erase == null)
                attrsStepper.stepper_icon_erase = ContextCompat.getDrawable(context, R.drawable.ic_erase)

            if (attrsStepper.stepper_icon_done == null)
                attrsStepper.stepper_icon_done = ContextCompat.getDrawable(context, R.drawable.ic_done)

            if (attrsStepper.stepper_icon_current == null)
                attrsStepper.stepper_icon_current = ContextCompat.getDrawable(context, R.drawable.ic_current)

            if (attrsStepper.stepper_icon_error == null)
                attrsStepper.stepper_icon_error = ContextCompat.getDrawable(context, R.drawable.ic_error)

            if (attrsStepper.stepper_icon_wait == null)
                attrsStepper.stepper_icon_wait = ContextCompat.getDrawable(context, R.drawable.ic_wait)

            if (attrsStepper.stepper_text_color_done == null)
                attrsStepper.stepper_text_color_done = Status.DONE.color

            if (attrsStepper.stepper_text_color_current == null)
                attrsStepper.stepper_text_color_current = Status.CURRENT.color

            if (attrsStepper.stepper_text_color_error == null)
                attrsStepper.stepper_text_color_error = Status.ERROR.color

            if (attrsStepper.stepper_text_color_wait == null)
                attrsStepper.stepper_text_color_wait = Status.WAITING.color



            /* Creating a example array to draw in preview of editor */
            stepList.add(
                Step(
                    1,
                    reso.getString(R.string.step1),
                    attrsStepper.stepper_text_color_current!!,
                    reso.getString(R.string.step1),
                    attrsStepper.stepper_text_color_done!!,
                    Status.DONE
                )
            )
            stepList.add(
                Step(
                    2,
                    reso.getString(R.string.step2),
                    attrsStepper.stepper_text_color_current!!,
                    reso.getString(R.string.step2),
                    attrsStepper.stepper_text_color_done!!,
                    Status.ERROR
                )
            )
            stepList.add(
                Step(
                    3,
                    reso.getString(R.string.step3),
                    attrsStepper.stepper_text_color_current!!,
                    reso.getString(R.string.step3),
                    attrsStepper.stepper_text_color_done!!,
                    Status.CURRENT
                )
            )
            stepList.add(
                Step(
                    4,
                    reso.getString(R.string.step4),
                    attrsStepper.stepper_text_color_current!!,
                    reso.getString(R.string.step4),
                    attrsStepper.stepper_text_color_done!!,
                    Status.WAITING
                )
            )
            stepList.add(
                Step(
                    5,
                    reso.getString(R.string.step5),
                    attrsStepper.stepper_text_color_current!!,
                    reso.getString(R.string.step5),
                    attrsStepper.stepper_text_color_done!!,
                    Status.WAITING
                )
            )

            orientation = VERTICAL

            setBackgroundColor(attrsStepper.stepper_background)

            paintSteps()

        }
    }

    private fun paintSteps() {
        this.removeAllViews()

        stepAttrsList = ArrayList()

        aux = 0f

        for (i in 0 until stepList.size) {
            val step = stepList[i] //Get step
            val stepv = ViewTools.getViewWithoutParent(createNewStep())
            val lp = LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
            )
            if (i != 0) {
                lp.setMargins(0, attrsStepper.stepper_space_between_steps.toInt(), 0, 0)
            }
            stepv.layoutParams = lp

            /* Get views from card */
            val iconView = stepv.findViewById<ImageView>(R.id.icon_step)
            val iconEdit = stepv.findViewById<ImageView>(R.id.icon_edit)
            val iconErase = stepv.findViewById<ImageView>(R.id.icon_erase)
            val textView = stepv.findViewById<AppCompatTextView>(R.id.step_text)

            /* Get icons from steps */
            val iconfs: Drawable? = when(step.status){
                Status.WAITING -> if (step.iconWait == null) attrsStepper.stepper_icon_wait else step.iconWait
                Status.ERROR -> if (step.iconError == null) attrsStepper.stepper_icon_error else step.iconWait
                Status.CURRENT -> if (step.iconCurrent == null) attrsStepper.stepper_icon_current else step.iconWait
                Status.DONE -> if (step.iconDone == null) attrsStepper.stepper_icon_done else step.iconWait
            }

            val iconErasefs: Drawable? = if (step.iconErase == null) attrsStepper.stepper_icon_erase else step.iconErase

            val iconEditfs: Drawable? = if (step.iconEdit == null) attrsStepper.stepper_icon_edit else step.iconEdit

            Glide.with(context).load(iconfs).fitCenter().circleCrop().into(iconView)
            Glide.with(context).load(iconErasefs).fitCenter().circleCrop().into(iconErase)
            Glide.with(context).load(iconEditfs).fitCenter().circleCrop().into(iconEdit)

            textView.text = when (step.status) {
                Status.DONE -> if (attrsStepper.stepper_text_type == Texts.SAME) attrsStepper.stepper_text_done else step.textDone
                Status.WAITING -> step.textWait
                Status.CURRENT -> step.textCurrent
                Status.ERROR -> if (attrsStepper.stepper_text_type == Texts.SAME) attrsStepper.stepper_text_error else step.textError
            }

            val iconViewColor: Int? = when (step.status) {
                Status.DONE -> if (step.iconDoneColorTint == null) attrsStepper.stepper_icon_color_done else step.iconDoneColorTint
                Status.WAITING -> if (step.iconWaitColorTint == null) attrsStepper.stepper_icon_color_wait else step.iconWaitColorTint
                Status.CURRENT -> if (step.iconCurrentColorTint == null) attrsStepper.stepper_icon_color_current else step.iconCurrentColorTint
                Status.ERROR -> if (step.iconErrorColorTint == null) attrsStepper.stepper_icon_color_error else step.iconErrorColorTint
            }

            val iconEraseColor: Int? = if (step.iconEraseColorTint == null) attrsStepper.stepper_icon_color_erase else step.iconEraseColorTint

            val iconEditColor: Int? = if (step.iconEditColorTint == null) attrsStepper.stepper_icon_color_edit else step.iconEditColorTint

            if (attrsStepper.stepper_icon_will_tint)
                iconView.setColorFilter(iconViewColor!!, PorterDuff.Mode.SRC_IN)

            if (attrsStepper.stepper_icon_edit_will_tint)
                iconEdit.setColorFilter(iconEraseColor!!, PorterDuff.Mode.SRC_IN)

            if (attrsStepper.stepper_icon_erase_will_tint)
                iconErase.setColorFilter(iconEditColor!!, PorterDuff.Mode.SRC_IN)

            val textColor: Int? = when (step.status) {
                Status.DONE -> if (step.textDoneColor == null) attrsStepper.stepper_text_color_done else step.textDoneColor
                Status.WAITING -> if (step.textWaitColor == null) attrsStepper.stepper_text_color_wait else step.textWaitColor
                Status.CURRENT -> if (step.textCurrentColor == null) attrsStepper.stepper_text_color_current else step.textCurrentColor
                Status.ERROR -> if (step.textErrorColor == null) attrsStepper.stepper_text_color_error else step.textErrorColor
            }

            textView.setTextColor(textColor!!)
            textView.textSize = attrsStepper.stepper_text_size
            val marg = context.resources.getDimension(R.dimen.icon_margin)

            aux += if (i == 0) marg else marg * 2 + attrsStepper.stepper_icon_size + attrsStepper.stepper_space_between_steps

            stepAttrsList.add(
                StepAttrs(
                    marg,
                    aux,
                    attrsStepper.stepper_icon_size,
                    iconViewColor!!
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

        loadLastField()

    }

    private fun createNewStep(): View =
        LayoutInflater.from(context).inflate(R.layout.item_layout, null, false)

    private fun removeParent(view: View): View {
        if (view.parent != null)
            (view as ViewGroup).removeView(view)
        return view
    }

    private fun loadLastField() {
        val temp = LayoutInflater.from(context).inflate(R.layout.template_layout, null, false)
        val til_newActivity = temp.findViewById<TextInputLayout>(R.id.tilNewNote)
        val et_newActivity = temp.findViewById<TextInputEditText>(R.id.etNewNote)
        val btn_newActivity = temp.findViewById<Button>(R.id.btnNewNote)

        /* Setting properties */
        til_newActivity.boxStrokeColor = attrsStepper.stepper_new_border_enable_color

        val states = arrayOf(
            intArrayOf(android.R.attr.state_focused),
            intArrayOf(-android.R.attr.state_focused),
            intArrayOf(android.R.attr.state_empty),
            intArrayOf(android.R.attr.state_enabled),
            intArrayOf(android.R.attr.state_activated),
            intArrayOf(-android.R.attr.state_pressed),
            intArrayOf(-android.R.attr.state_window_focused),
            intArrayOf(-android.R.attr.state_active)
        )

        val colors = intArrayOf(
            attrsStepper.stepper_new_border_enable_color,
            attrsStepper.stepper_new_border_disable_color,
            attrsStepper.stepper_new_border_disable_color,
            attrsStepper.stepper_new_border_disable_color,
            attrsStepper.stepper_new_border_disable_color,
            attrsStepper.stepper_new_border_disable_color,
            attrsStepper.stepper_new_border_disable_color,
            attrsStepper.stepper_new_border_disable_color
        )

        /*Custom edit text layout of new Activity */
        til_newActivity.hintTextColor = ColorStateList(states, colors)
        til_newActivity.defaultHintTextColor = ColorStateList(states, colors)
        til_newActivity.hint = attrsStepper.stepper_text

        /* Custom edit text of new Activity */
        et_newActivity.setTextColor(attrsStepper.stepper_new_text_color)
        et_newActivity.setTextSize(TypedValue.COMPLEX_UNIT_PX, attrsStepper.stepper_new_text_size)

        /* Custom btn Add new Activity */
        btn_newActivity.setTextSize(
            TypedValue.COMPLEX_UNIT_PX,
            attrsStepper.stepper_new_button_text_size
        )
        btn_newActivity.setTextColor(attrsStepper.stepper_new_text_color)
        btn_newActivity.isAllCaps = attrsStepper.stepper_new_button_allcaps
        btn_newActivity.text = attrsStepper.stepper_text_button

        /* Create a layout params of edit text layout */
        val lpet = til_newActivity.layoutParams as LayoutParams
        lpet.setMargins(
            attrsStepper.stepper_new_margin_start.toInt(),
            attrsStepper.stepper_new_margin_top.toInt(),
            attrsStepper.stepper_new_margin_end.toInt(),
            attrsStepper.stepper_new_margin_bottom.toInt()
        )
        til_newActivity.layoutParams = lpet

        /* Adding views */
        this.addView(ViewTools.getViewWithoutParent(temp))

        /* Adding Events */
        btn_newActivity.setOnClickListener {
            val textNew = et_newActivity.text.toString().trim()
            if (textNew.isEmpty())
                et_newActivity.error = context.getString(R.string.new_activity_error)
            else {
                //add Activity
                val stepn = Step(
                    stepList.size + 1,
                    textNew,
                    0,
                    context.getString(R.string.step1),
                    0,
                    Status.WAITING
                )
                this@Stepper.stepList.add(stepn)
                stepperEvent?.stepAdded(stepn)
                et_newActivity.text = null
                paintSteps()
            }
        }
        this@Stepper.addView(ViewTools.getViewWithoutParent(temp))
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (canvas == null)
            return

        for (i in 0 until stepList.size - 1) {
            val nextStep = stepList[i + 1]
            val stepAttr = stepAttrsList[i]
            val nextStepAttr = stepAttrsList[i + 1]
            var half = NumberHelper.getHalfNumber(attrsStepper.stepper_line_width) * -1

            when (attrsStepper.stepper_line_tintMode) {
                TintMode.SOLID -> paint.color =
                    if (nextStep.status != Status.WAITING) attrsStepper.stepper_line_color_done!! else attrsStepper.stepper_line_color_wait!!
                TintMode.DEGRADED -> paint.shader = LinearGradient(
                    0f,
                    0f,
                    0f,
                    attrsStepper.stepper_space_between_steps * .5f,
                    stepAttr.color,
                    nextStepAttr.color,
                    Shader.TileMode.MIRROR
                )
                TintMode.NONE -> {
                }
            }

            if (attrsStepper.stepper_line_tintMode != TintMode.NONE)
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