package com.space.quizapp.domain.model



data class QuizQuestionDomainModel(
    val subjectId: Int = 0,
    val questionTitle: String,
    val data: List<Answer> = emptyList(),
    val point:Int,
    val correctAnswer: Int,
    val questionIndex: Int
){
    data class Answer(
        val answer:String,
        val lastQuestion:Boolean
    )
}