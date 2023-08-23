package com.space.quizapp.domain.usecase.quiz.get.quiz_gpa

import com.space.quizapp.domain.model.QuizPointsDomainModel
import com.space.quizapp.domain.repository.QuizRepository

class GetQuizForGpaUseCaseImpl(private val quizRepository: QuizRepository):GetQuizForGpaUseCase {
    override suspend fun invoke(userId: String): List<QuizPointsDomainModel> {
        return quizRepository.getQuizByUserId(userId)
    }
}
