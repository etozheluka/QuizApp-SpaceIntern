package com.space.quizapp.presentation.ui.quiz.custom_view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.space.quizapp.R
import com.space.quizapp.presentation.ui.base.custom_view.QuizBaseCustomView

/**
 * A custom view that draws a blue vector on the top.
 */
class QuizQuestionTopBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0) :
    QuizBaseCustomView(context, attrs, defStyleAttr) {

    private val paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
        color = ContextCompat.getColor(context, R.color.blue_secondary_lightest)
    }

    override fun startDrawing(canvas: Canvas, width: Float, height: Float) {


        canvas.drawRoundRect(RectF(0f, 0f, width, height), 90f, 90f, paint)
    }
}
