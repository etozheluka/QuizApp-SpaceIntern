package com.space.quizapp.presentation.model

data class QuizQuestionUIModel(
    val subjectId: Int,
    val questionTitle: String,
    val data: List<Answer> = emptyList(),
    val point:Int,
    val correctAnswer: Int,
    val questionIndex: Int
){
    data class Answer(
        val answer:String,
        val correctAnswer: Int,
        val point: Int,
        val lastQuestion:Boolean
        )
}