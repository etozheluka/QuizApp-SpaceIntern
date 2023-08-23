package com.space.quizapp.domain.usecase.quiz.get.quiz

import com.space.quizapp.domain.model.QuizDomainModel
import com.space.quizapp.domain.repository.QuizRepository
import com.space.quizapp.utils.network.RequestResource

class GetQuizUseCaseImpl(private val quizRepository: QuizRepository) : GetQuizUseCase {
    override suspend fun invoke(): RequestResource<List<QuizDomainModel>> {
        return quizRepository.getQuiz()
    }
}