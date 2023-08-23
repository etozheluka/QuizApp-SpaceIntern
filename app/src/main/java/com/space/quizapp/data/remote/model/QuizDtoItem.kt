package com.space.quizapp.data.remote.model


data class QuizDtoItem(
    val id: Int = 0,
    val image: String? = "",
    val questions: List<QuestionDto> = emptyList(),
    val questionsCount: Int = 0,
    val quizDescription: String? = "",
    val quizTitle: String? = ""
) {
    data class QuestionDto(
        val answers: List<String>,
        val lastQuestion:Boolean,
        val point:Int,
        val correctAnswer: Int,
        val questionIndex: Int,
        val questionTitle: String
    )
}