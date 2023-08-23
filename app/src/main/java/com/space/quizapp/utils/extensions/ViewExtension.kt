package com.space.quizapp.utils.extensions

import android.content.res.ColorStateList
import android.view.View

fun View.setTint(colorRes:Int){
    this.backgroundTintList = ColorStateList.valueOf(this.context.getColor(colorRes))
}