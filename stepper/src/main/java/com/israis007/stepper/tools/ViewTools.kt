package com.israis007.stepper.tools

import android.view.View
import android.view.ViewGroup

class ViewTools {

    companion object {
        fun getViewWithoutParent(view: View): View {
            if (view.parent != null)
                (view.parent as ViewGroup).removeView(view)
            return view
        }
    }
}