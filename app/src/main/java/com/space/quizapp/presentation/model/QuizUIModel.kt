package com.space.quizapp.presentation.model


data class QuizUIModel(
    val id: Int,
    val image: String?,
    val questionsCount: Int,
    val quizDescription: String?,
    val quizTitle: String?
)
