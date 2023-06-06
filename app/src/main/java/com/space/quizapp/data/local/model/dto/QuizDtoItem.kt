package com.space.quizapp.data.local.model.dto

data class QuizDtoItem(
    val id: String?,
    val image: String?,
    val questions: List<QuestionDto>,
    val questionsCount: Int?,
    val quizDescription: String?,
    val quizTitle: String?
) {
    data class QuestionDto(
        val answers: List<String>,
        val correctAnswer: String?,
        val questionIndex: Int?,
        val questionTitle: String?
    )
}