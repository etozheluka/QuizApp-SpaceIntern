package com.space.quizapp.utils.extensions

import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes

/**
 * Set colored text with prefix
 */
fun TextView.setColoredTextWithPrefix(prefix: String, text: String, @ColorInt color: Int) {
    val spannableString = SpannableString("$prefix$text")
    val colorSpan = ForegroundColorSpan(color)

    spannableString.setSpan(
        colorSpan,
        prefix.length,
        prefix.length + text.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )

    this.text = spannableString
}
fun TextView.setTextColorCompat(@ColorRes colorRes: Int) {
    this.setTextColor(context.getColor(colorRes))
}
