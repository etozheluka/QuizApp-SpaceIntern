package com.space.quizapp.domain.usecase.quiz.get.quiz

import com.space.quizapp.domain.model.QuizDomainModel
import com.space.quizapp.utils.network.RequestResource


interface GetQuizUseCase {
    suspend fun invoke(): RequestResource<List<QuizDomainModel>>
}
