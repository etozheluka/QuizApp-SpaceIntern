package com.space.quizapp.domain.model

data class QuizPointsDomainModel (
    val id: Int,
    val image: String?,
    val quizDescription: String?,
    val quizTitle: String?,
    val quizPoints: Int
)