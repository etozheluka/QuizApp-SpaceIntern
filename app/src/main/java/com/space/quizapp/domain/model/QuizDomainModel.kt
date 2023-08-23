package com.space.quizapp.domain.model

data class QuizDomainModel(
    val id: Int,
    val image: String?,
    val questionsCount: Int,
    val quizDescription: String?,
    val quizTitle: String?
)
