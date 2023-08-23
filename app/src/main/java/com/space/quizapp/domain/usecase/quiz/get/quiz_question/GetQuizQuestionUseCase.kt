package com.space.quizapp.domain.usecase.quiz.get.quiz_question

import com.space.quizapp.domain.model.QuizQuestionDomainModel

interface GetQuizQuestionUseCase {
    suspend fun invoke(id:Int): List<QuizQuestionDomainModel>
}